import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class ConfHall2_LeetCode {
    public static void main(String[] args) throws IOException {
        ConfHall2_LeetCode leetCode = new ConfHall2_LeetCode();
        int[][] arr = new int[][]{
                {1, 10},
                {2, 10},
                {3, 10},
                {4, 10},
                {5, 10},
                {6, 10},
                {7, 10}
        };
        File file = new File("input/in.txt");
        String s = Files.readString(Paths.get(file.getPath()));
        String[] r = s.split("\\[");
        List<int[]> list = new ArrayList<>();
        for (String w : r) {
            if (w.isBlank()) {
                continue;
            }
            w = w.replaceAll("\\]", "").trim();
            String[] p = w.split("\\,");
            list.add(new int[]{Integer.parseInt(p[0]), Integer.parseInt(p[1])});
        }
        Comparator<int[]> comparator = Comparator.comparingDouble(o -> o[0]);
        Collections.sort(list, comparator);
        int[][] ary = new int[list.size()][2];
        for (int i = 0; i < list.size(); i++) {
            ary[i] = list.get(i);
//            System.out.println(Arrays.toString(ary[i]));
        }
        System.out.println(leetCode.mostBooked(100, ary));
//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//        reader.readLine();
//        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
//        writer.write(0);
    }

    public int mostBooked(int n, int[][] meetings) {
        Comparator<Meeting> meetingComparator = Comparator.comparing(o -> o.start);
        Comparator<Meeting> meetingComparator1 = Comparator.comparing(o -> o.end);
        PriorityQueue<Meeting> meetingPriorityQueue = new PriorityQueue<>(meetingComparator);
        PriorityQueue<Room> roomPriorityQueue = new PriorityQueue<>();
        Comparator<int[]> comparator = Comparator.comparing(o -> o[0]);
        Arrays.sort(meetings, comparator);
        List<Meeting> list = new ArrayList<>();
        int startTime = meetings[0][0];
        for (int i = 0; i < meetings.length; i++) {
            Meeting meeting = new Meeting();
            meeting.start = meetings[i][0];
            meeting.end = meetings[i][1];
            meeting.number = i;
            meeting.s = "" + meeting.start + " " + meeting.end;
            meetingPriorityQueue.add(meeting);
            list.add(meeting);
        }
        for (int i = 0; i < n; i++) {
            Room room = new Room();
            room.number = i;
            roomPriorityQueue.add(room);
        }
        PriorityQueue<Meeting> stack = new PriorityQueue<>(meetingComparator1);
        boolean a = false;
        while (!meetingPriorityQueue.isEmpty() || !stack.isEmpty()) {
            Meeting meeting = meetingPriorityQueue.peek();
            Room room = roomPriorityQueue.peek();
            if (meeting != null && room != null && !a) {
                Meeting meeting2 = meetingPriorityQueue.peek();
                if (stack.peek() != null && stack.peek().end <= meeting2.start) {
                    roomPriorityQueue.add(stack.poll().room);
                    continue;
                }
                meeting2 = meetingPriorityQueue.poll();
                meeting2.room = roomPriorityQueue.poll();
                meeting2.room.meetingsCount++;
                stack.add(meeting2);
//                System.out.println("tostack! time=" + startTime + " roommumber=" + room.number +
//                        " meetnumber=" + meeting2.number + " meetstart=" + meeting2.start +
//                        " meetend=" + meeting2.end + "***" + meeting2.s);
            } else {
                Meeting meeting1 = meetingPriorityQueue.peek();
                a = true;
                if (meeting1 != null) {
                    if (stack.peek() != null && stack.peek().end <= meeting1.start) {
                        roomPriorityQueue.add(stack.poll().room);
                        continue;
                    }
                    Meeting meeting2 = stack.poll();
                    List<Meeting> l = new ArrayList<>();
                    List<Room> l1 = new ArrayList<>();
                    roomPriorityQueue.add(meeting2.room);
                    while (stack.peek() != null && stack.peek().end == meeting2.end) {
                        Meeting m = stack.poll();
                        roomPriorityQueue.add(m.room);
                        l.add(m);
                        l1.add(m.room);
                    }
                    meeting1 = meetingPriorityQueue.poll();
                    meeting1.room = roomPriorityQueue.poll();
                    roomPriorityQueue.removeAll(l1);
                    meeting1.room.meetingsCount++;
                    int t = meeting1.end - meeting1.start;
                    if (meeting2.end > meeting1.start) {
                        meeting1.start = meeting2.end;
                        meeting1.end = meeting1.start + t;
                    }
                    stack.add(meeting1);
//                    System.out.println("                         fromstack! time=" + startTime + " roommumber=" +
//                            meeting1.room.number +
//                            " meetnumber=" + meeting1.number + " meetend=" + meeting1.end +
//                            " meetingstart=" + meeting1.start + "***" + meeting1.s);
                    stack.addAll(l);
                } else {
                    roomPriorityQueue.add(stack.poll().room);
                }
            }
        }
        Comparator<Room> comparator1 = Comparator.comparing(o -> o.meetingsCount);
        List<Room> rooms = new ArrayList<>(roomPriorityQueue);
        Collections.sort(rooms, comparator1);
        int res = rooms.get(rooms.size() - 1).meetingsCount;
        System.out.println(rooms);
        int result = Integer.MAX_VALUE;
        for (Room r : rooms) {
            if (r.meetingsCount == res) {
                result = Integer.min(result, r.number);
            }
        }
        return result;
    }

    static class Meeting {
        int start;
        int end;
        Room room;
        int number;
        String s;

        public String toString() {
            return "start=" + start + " " + "end=" + end + " number=" + number;
            //" roomnumber" + room.number;
        }
    }

     class Room implements Comparable<Room> {

        int number;
        int meetingsCount;

        @Override
        public int compareTo(Room o) {
            if (this.number > o.number) {
                return 1;
            } else if (this.number < o.number) {
                return -1;
            }
            return 0;
        }

        public boolean equals(Room o) {
            if (this == o) {
                return true;
            }
            return this.number == o.number;
        }

        @Override
        public int hashCode() {
            return Integer.hashCode(number);
        }

        @Override
        public String toString() {
            return "number=" + number + " " + "count=" + meetingsCount;
        }
    }
}
