public class Exe {
    public static void main(String[] args) {
        String message = "New Compute User,\n" +
                "\n" +
                "Thank for requesting a price estimate from the Google Cloud Platform.\n" +
                "\n" +
                "Estimate:\n" +
                "\n" +
                "\n" +
                "4 x \n" +
                "{item.display_description}}</td>\n" +
                "2920 total hours per month</td>\n" +
                "1,082.77\n" +
                "\n" +
                "\n" +
                "\n" +
                "Total *:\n" +
                "1,082.77\n" +
                "\n" +
                "* The estimated fees provided by the Cloud Pricing Calculator are for discussion purposes only and are not binding on either you or Google.  Your actual fees may be higher or lower than the estimate. A more detailed and specific list of fees will be provided at time of sign-up.  To sign up for the Cloud Platform and purchase services, please click on one of the product links above.";


        String[] arrayWords = message.split(":", 2);
        String[] arrayWords2 = arrayWords[1].split(":", 2);
        String[] arrayWords3 = arrayWords2[1].split("\n\n", 2);
        System.out.println(arrayWords3[0].trim().equals("1,082.77"));
    }
}
