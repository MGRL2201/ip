public class Parser {

    public static String parse(String input) {
        String inputLower = input.toLowerCase();
        if (input.replaceAll("\\s","").toLowerCase().equals("bye")) {
            return "bye";
        } else if (inputLower.replaceAll("\\s", "").equals("list")) {
            return "list";
        } else if (inputLower.length() > 3 && inputLower.substring(0, 4).equals("done")) {
            return "done";
        } else if (inputLower.length() > 5 && inputLower.substring(0, 6).equals("delete")) {
            return "delete";
        } else if (inputLower.length() >= 4 && inputLower.substring(0, 4).equals("todo")) {
            return "todo";
        } else if (inputLower.length() >= 5 && inputLower.substring(0, 5).equals("event")) {
            return "event";
        }  else if (input.length() >= 8 && inputLower.substring(0, 8).equals("deadline")) {
            return "deadline";
        } else {
            return "InvalidCommand";
        }
    }

}
