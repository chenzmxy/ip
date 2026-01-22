public class UI {
    private static final String LINE =
            "————————————————————————————————";
    public void greeting() {
        System.out.println(LINE + "\n"
                        + "Hello! I'm ComSci\n"
                        + "What can I do for you?\n"
                        + LINE
        );
    }

    public void echo(String s) {
        System.out.println(
                LINE + "\n"
                + s + "\n"
                + LINE
        );
    }

    public void showBye() {
        System.out.println(LINE + "\n"
                        + "Bye. Hope to see you again soon!\n"
                        + LINE
        );
    }
}
