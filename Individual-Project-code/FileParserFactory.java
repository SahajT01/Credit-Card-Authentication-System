public class FileParserFactory {
    public static FileParser createFileParser(String filePath) {
        if (filePath.endsWith(".csv")) {
            return new CSVFileParser();
        } else if (filePath.endsWith(".json")) {
            return new JSONFileParser();
        } else if (filePath.endsWith(".xml")) {
            return new XMLFileParser();
        }
        throw new IllegalArgumentException("Unsupported file format");
    }
}
