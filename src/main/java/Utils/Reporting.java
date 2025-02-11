package Utils;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.*;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Reporting {

    private String featurefile = "";
    private String feature = "";
    private String scenario = "";
    private String step = "";
    private String env = "";
    private String url = "";
    private String finalResult = "";

    private String baseHtmlTemplate = "<!DOCTYPE html>\n" +
            "<html lang=\"en\">\n" +
            "<head>\n" +
            "    <meta charset=\"UTF-8\">\n" +
            "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
            "    <title>Automation Test Report</title>\n" +
            "    <style>\n" +
            "        body {\n" +
            "            font-family: Arial, sans-serif;\n" +
            "            margin: 20px;\n" +
            "        }\n" +
            "        .header {\n" +
            "            background-color: #0073e6;\n" +
            "            color: white;\n" +
            "            padding: 15px;\n" +
            "            text-align: center;\n" +
            "            border-radius: 5px;\n" +
            "        }\n" +
            "        .header h1, .header h2 {\n" +
            "            margin: 5px;\n" +
            "        }\n" +
            "        .info {\n" +
            "            margin: 10px 0;\n" +
            "            padding: 10px;\n" +
            "            border: 1px solid #ddd;\n" +
            "            border-radius: 5px;\n" +
            "            background-color: #f9f9f9;\n" +
            "        }\n" +
            "        .test-steps {\n" +
            "            width: 100%;\n" +
            "            border-collapse: collapse;\n" +
            "            margin-top: 20px;\n" +
            "        }\n" +
            "        .test-steps th, .test-steps td {\n" +
            "            border: 1px solid #ddd;\n" +
            "            padding: 10px;\n" +
            "            text-align: left;\n" +
            "        }\n" +
            "        .test-steps th {\n" +
            "            background-color: #0073e6;\n" +
            "            color: white;\n" +
            "        }\n" +
            "        .step-name {\n" +
            "            word-wrap: break-word;\n" +
            "            max-width: 400px;\n" +
            "        }\n" +
            "        .result {\n" +
            "            font-weight: bold;\n" +
            "        }\n" +
            "        .pass {\n" +
            "            color: green;\n" +
            "        }\n" +
            "        .fail {\n" +
            "            color: red;\n" +
            "        }\n" +
            "        .screenshot img {\n" +
            "            max-width: 100%;\n" +
            "            height: auto;\n" +
            "            margin-top: 10px;\n" +
            "            border: 1px solid #ddd;\n" +
            "            border-radius: 5px;\n" +
            "        }\n" +
            "    </style>\n" +
            "</head>\n" +
            "<body>\n" +
            "    <div class=\"header\">\n" +
            "        <h1>/*feature*/</h1>\n" +
            "        <h2>/*Scenario*/</h2>\n" +
            "    </div>\n" +
            "    <div class=\"info\">\n" +
            "        <p><strong>Enviroment:</strong>/*env*/</p>\n" +
            "        <p><strong>URL:</strong> <a href=\"/*url*/\">/*url*/</a></p>\n" +
            "    </div>\n" +
            "    <table class=\"test-steps\">\n" +
            "        <tr>\n" +
            "            <th>Step Name</th>\n" +
            "            <th>Result</th>\n" +
            "        </tr>\n" +
            "        /*result*/\n" +
            "    </table>\n" +
            "</body>\n" +
            "</html>\n";


    public void moveExistingReport() {

        try {
            Path sourcePath = Paths.get("src/test/resources/Reports/Results/Custom Reports");
            Path destinationPath = Paths.get("src/test/resources/Reports/Results/Custom Reports/Archive Custom Reports");

            // Ensure the destination directory exists
            if (!Files.exists(destinationPath)) {
                Files.createDirectories(destinationPath);
            }

            // Move each .html file from source to destination
            DirectoryStream<Path> stream = Files.newDirectoryStream(sourcePath, "*.html");
            for (Path file : stream) {
                Path targetFile = destinationPath.resolve(file.getFileName());
                Files.move(file, targetFile, StandardCopyOption.REPLACE_EXISTING);
            }
            System.out.println("All Existing Reports have been moved successfully.");
        } catch (IOException e) {
            System.err.println("Error Moving Existing Reports: " + e.getMessage());
        }
    }

    public void setValues(String featurefile, String featureName, String scenarioName, String env, String url) {
        this.featurefile = featurefile;
        this.feature = featureName;
        this.scenario = scenarioName;
        this.env = env;
        this.url = url;
    }


    public void logReport(String stepname, Boolean result) {

        String baseResult = "<tr>\n" +
                "            <td class=\"step-name\">" + stepname + "</td>\n" +
                "            <td class=\"result pass\">" + result + "</td>\n" +
                "        </tr>\n" +
                "        <tr class=\"screenshot\">\n" +
                "            <td colspan=\"2\"><img src=\"screenshot1.png\" alt=\"Step Screenshot\"></td>\n" +
                "        </tr>";
        finalResult += baseResult;
        System.out.println("Printing from logReport " + finalResult);

    }

    public String generateReport() {

        System.out.println("Printing from Report ggg" + feature + scenario + env + url + finalResult);
        return baseHtmlTemplate
                .replace("/*feature*/", feature)
                .replace("/*Scenario*/", scenario)
                .replace("/*env*/", env)
                .replace("/*url*/", url)
                .replace("/*result*/", finalResult);

    }

    public void saveReport() {
        System.out.println("Printing from SaveReport " + finalResult);
        String timestamp = new SimpleDateFormat("ddMMyyyy_HHmmss").format(new Date());
        String reportFileName = featurefile + "_" + scenario.replace(" ", "_") + "_" + timestamp + ".html";
        String filePath = "src/test/resources/Reports/Results/Custom Reports/" + reportFileName;

        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(generateReport());
            System.out.println("Report saved at: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
