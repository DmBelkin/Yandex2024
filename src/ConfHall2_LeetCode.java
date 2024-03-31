import java.util.*;

public class ConfHall2_LeetCode {

    public static void main(String[] args) {
        int[][] arr = new int[][]{
                {39, 49},
                {28, 39},
                {9, 29},
                {10, 36},
                {22, 47},
                {2, 3},
                {4, 49},
                {46, 50},
                {45, 50},
                {17, 33}
        };
        System.out.println(mostBooked(3, arr));
    }

    public static int mostBooked(int n, int[][] meetings) {
        Comparator<Room> roomComparator = Comparator.comparing(o -> o.number);
        Comparator<Meeting> meetingComparator = Comparator.comparing(o -> o.start);
        Comparator<Meeting> meetingComparator1 = Comparator.comparing(o -> o.end);
        PriorityQueue<Meeting> meetingPriorityQueue = new PriorityQueue<>(meetingComparator);
        PriorityQueue<Room> roomPriorityQueue = new PriorityQueue<>(roomComparator);
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
        while (!meetingPriorityQueue.isEmpty() || !stack.isEmpty()) {
            Meeting meeting = meetingPriorityQueue.peek();
            Meeting meeting1 = stack.peek();
            if (meeting1 != null && startTime == meeting1.end) {
                while (!stack.isEmpty() && stack.peek().end == startTime) {
                    Meeting meeting2 = stack.poll();
                    roomPriorityQueue.add(meeting2.room);
                    System.out.println("                         fromstack! time=" + startTime + " roommumber=" + meeting2.room.number +
                            " meetnumber=" + meeting2.number + " meetend=" + meeting2.end +
                            " meetingstart=" + meeting2.start + "***" + meeting2.s);
                }
            }
            if (meeting != null && startTime == meeting.start) {
                Room room = roomPriorityQueue.peek();
                if (room != null) {
                    while (!meetingPriorityQueue.isEmpty() && meetingPriorityQueue.peek().start == startTime
                            && !roomPriorityQueue.isEmpty()) {
                        Meeting meeting2 = meetingPriorityQueue.poll();
                        meeting2.room = roomPriorityQueue.poll();
                        meeting2.room.meetingsCount++;
                        stack.add(meeting2);
                        System.out.println("time=" + startTime + " roommumber=" + room.number +
                                " meetnumber=" + meeting2.number + " meetstart=" + meeting2.start +
                                " meetend=" + meeting2.end + "***" + meeting2.s);
                    }
                }
            }
            Iterator<Meeting> iterator = meetingPriorityQueue.iterator();
            while (iterator.hasNext()) {
                Meeting m = iterator.next();
                if (m.start == startTime) {
                    m.start++;
                    m.end++;
                }
            }
            startTime++;
        }
        System.out.println(stack);
        System.out.println(roomPriorityQueue);
        System.out.println(list);
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
            return "start=" + start + " " + "end=" + end + " number=" + number + "**" + s;
            //" roomnumber" + room.number;
        }
    }

    static class Room {
        int number;
        int meetingsCount;
        public String toString() {
            return "number=" + number + " " + "count=" + meetingsCount;
        }
    }

}
