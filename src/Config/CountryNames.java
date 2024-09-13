package Config;

public class CountryNames {
    public static final String[] continentNames2 = new String[] {"Rothenor", "Xandria", "Veloria", "Mythoria"};
    public static String[] countryNames2 = new String[] {
            // Rothenor
            "Aldaris", "Dornfall", "Korinthar", "Mirgath", "Sylvanis", "Drakenhelm",
            // Xandria
            "Zephyros", "Thalrion", "Eryndor", "Valkaris", "Lythor", "Kryndor",
            // Veloria
            "Velstrom", "Galdoria", "Morathis", "Fenris", "Volcrath", "Zytheris",
            // Mythoria
            "Nyxora", "Aetheris", "Ravenscar", "Solthar", "Myrenor", "Eldarion"
    };

    public static String[] continentNames3 = new String[] {"Loria", "Zyrath", "Vendros", "Myrion"};
    public static String[] countryNames3 = {
            // Loria
            "Faldris", "Eryndell", "Kovaris", "Thandor", "Velanthor", "Myrvalis",
            // Zyrath
            "Kalmaris", "Vornath", "Lyssara", "Zenthar", "Orindor", "Xyldar",
            // Vendros
            "Tirandor", "Valkir", "Drathmoor", "Falandir", "Korvath", "Lyndor",
            // Myrion
            "Arkendell", "Sylthar", "Zorwyn", "Nimaris", "Therond", "Valthorin"
    };

    public static String[] continentNames4 = new String[] {"North America", "South America", "Europe", "Africa", "Asia", "Australia"};
    public static String[] countryNames4 = {
            // North America 1-9
            "Alaska", "Northwest Territory", "Greenland", "Alberta", "Ontario", "Quebec",
            "Western US", "Eastern US", "Central America",
            // South America 10-13
            "Venezuela", "Peru", "Brazil", "Argentina",
            // Europe 14-20
            "Iceland", "Scandinavia", "Ukraine", "Great Britain", "Northern Europe", "Western Europe", "Southern Europe",
            // Africa 21-26
            "North Africa", "Egypt", "Congo", "East Africa", "South Africa", "Madagascar",
            // Asia 27-38
            "Siberia", "Yakutsk", "Kamchatka", "Ural", "Irkutsk", "Mongolia", "Afghanistan",
            "China", "Japan", "Middle East", "India", "Siam",
            // Australia 39-42
            "Indonesia", "New Guinea", "Western Australia", "Eastern Australia"
    };

    public static String getCorrectContinent(String country, String board) {
        switch (board) {
            case "board2" -> {
                return switch (country) {
                    case "Aldaris", "Dornfall", "Korinthar", "Mirgath", "Sylvanis", "Drakenhelm" -> continentNames2[0];
                    case "Zephyros", "Thalrion", "Eryndor", "Valkaris", "Lythor", "Kryndor" -> continentNames2[1];
                    case "Velstrom", "Galdoria", "Morathis", "Fenris", "Volcrath", "Zytheris" -> continentNames2[2];
                    case "Nyxora", "Aetheris", "Ravenscar", "Solthar", "Myrenor", "Eldarion" -> continentNames2[3];
                    default -> "No Continent";
                };
            }
            case "board3" -> {
                return switch (country) {
                    case "Faldris", "Eryndell", "Kovaris", "Thandor", "Velanthor", "Myrvalis" -> continentNames3[0];
                    case "Kalmaris", "Vornath", "Lyssara", "Zenthar", "Orindor", "Xyldar" -> continentNames3[1];
                    case "Tirandor", "Valkir", "Drathmoor", "Falandir", "Korvath", "Lyndor" -> continentNames3[2];
                    case "Arkendell", "Sylthar", "Zorwyn", "Nimaris", "Therond", "Valthorin" -> continentNames3[3];
                    default -> "No Continent";
                };
            }
            case "board4" -> {
                return switch (country) {
                    case "Alaska", "Northwest Territory", "Greenland", "Alberta", "Ontario", "Quebec",
                         "Western US", "Eastern US", "Central America" -> continentNames4[0];
                    case "Venezuela", "Peru", "Brazil", "Argentina" -> continentNames4[1];
                    case "Iceland", "Scandinavia", "Ukraine", "Great Britain", "Northern Europe", "Western Europe",
                         "Southern Europe" -> continentNames4[2];
                    case "North Africa", "Egypt", "Congo", "East Africa", "South Africa", "Madagascar" ->
                            continentNames4[3];
                    case "Siberia", "Yakutsk", "Kamchatka", "Ural", "Irkutsk", "Mongolia", "Afghanistan",
                         "China", "Japan", "Middle East", "India", "Siam" -> continentNames4[4];
                    case "Indonesia", "New Guinea", "Western Australia", "Eastern Australia" -> continentNames4[5];
                    default -> "No Continent";
                };
            }
        }
        return "No Continent";
    }
}
