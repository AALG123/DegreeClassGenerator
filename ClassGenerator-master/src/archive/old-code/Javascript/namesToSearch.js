// List of names to search
const namesToSearch = [
    "Gutierrez",
    "Smith",
    "Silva",
    "Barrilleaux",
    "Evans",
    "Gerrish",
    "Kiesselbach",
    "Hernandez",
    "Laderman",
    "Clifford Jr",
    "Clemente Jr",
    "Smith",
    "Tuttle",
    "Trafalis",
    "Yoon",
    "Shih",
    "Firpo",
    "Bera",
    "McClain",
    "Watts",
    "Adams",
    "Lee",
    "DeHaan",
    "McDevitt",
    "Barberini",
    "Lethin",
    "Aurilio",
    "Bruxvoort",
    "Musawwir",
    "Wimmer",
    "Egan",
    "La Rochelle",
    "Weinstein",
    "Wiese",
    "Redman",
    "Stanford",
    "Greedy",
    "Rasmussen",
    "Hunt",
    "Conaway",
    "Irish",
    "Daus - Magbual",
    "Inman",
    "Ambuehl",
    "Weeks",
    "Noel",
    "Upadhyay",
    "Behravesh",
    "LaRocca",
    "Parra",
    "Johnson",
    "Escalambre",
    "Yee",
    "Roman-Medina",
    "Moore",
    "Santos",
    "McClain",
    "Watts",
    "Adams",
    "Moore",
    "Campbell",
    "Ansari",
    "Kiesselbach",
    "Hui",
    "Barrientos",
    "Lujan",
    "Lujan",
    "Santos",
    "Johnston",
    "Davalos",
    "Victoria",
    "Justo",
    "Honeywell",
    "Hernandez",
    "Urbina",
    "DeMello",
    "Thomas",
    "Tariq",
    "Romero",
    "Chen",
    "Reed",
    "Martin",
    "Delgado-Salazar",
    "De La Cruz",
    "Nieve",
    "Kalyanaraman",
    "Gazulla Garanto",
    "Mpandenyama",
    "Castro",
    "Guerrero-Betteo",
    "Speight",
    "Corrigan",
    "Verissimo Ira",
    "Stephens",
    "Mitchell",
    "Hemstreet",
    "Rizzo",
    "Palmer",
    "Candrian",
    "Liu",
    "Bernardo",
    "Powers",
    "Gaudio",
    "Schulke Jr",
    "Lubarsky",
    "Raskin",
    "Aurilio",
    "Pirehabdollahkandi",
    "Cortes",
    "Del Prado",
    "Hicks",
    "Schulze",
    "Eiland",
    "Devera",
    "Fullerton",
    "Raskin",
    "Cardenas",
    "Perez",
    "Linares",
    "Negrete",
    "Quach",
    "Lynch",
    "Teclemariam",
    "Lucas",
    "Gomez",
    "Rao",
    "Macias",
    "Alexander",
    "Haick II",
    "Lewis",
    "Giambruno",
    "Shieh",
    "Schubert",
    "Salari",
    "Biglari",
    "Kaslan",
    "DeMello",
    "Rivera Contreras",
    "Ciesla",
    "Parajon Puenzo",
    "Mckay",
    "Haugen",
    "Simon",
    "Pascual",
    "Hollandsworth",
    "Bautista",
    "Banini",
    "Low",
    "Spring",
    "De Jarnatt",
    "Liu",
    "Martha",
    "Tohmc",
    "Gottlieb",
    "Deal",
    "Shea",
    "Martinez Luna",
    "Stapleton",
    "Eck",
    "Afshar",
    "Herndon",
    "Ikeda",
    "Ng",
    "Gardini",
    "Takayama",
    "Carroll",
    "Clemens",
    "Karimi",
    "Belenky",
    "Deretsky",
    "Iqbal",
    "Ferrer Clotas",
    "Villalba",
    "Matthews",
    "Slapin Lufkin",
    "VanSlambrouck",
    "ROBINSON",
    "Teng",
    "Van Dongen",
    "Pack",
    "Kern",
    "Ichimura",
    "Levine",
    "Espinoza",
    "VonBleichert",
    "Viertel",
    "Nelson",
    "Heath",
    "Dorsett",
    "Li",
    "Flewellen",
    "Snyder",
    "Li",
    "Fang",
    "Korniakov",
    "Pon",
    "Reitz",
    "Marcus",
    "Stevens",
    "Lunn",
    "Mirassou",
    "Vorobey",
    "Matsuo",
    "Maynard",
    "Chaney",
    "McCarney",
    "Schneider",
    "Jones",
    "Hemstreet",
    "Levesque",
    "Robles",
    "Medrano",
    "Baker",
    "Salido",
    "Beltran",
    "Burger",
    "Giambruno",
    "Gustlin",
    "Angle",
    "Whyte",
    "Davis",
    "Diamond",
    "Young",
    "Patki",
    "Anttila",
    "Kanaaneh",
    "McDaniel",
    "Mackusick",
    "Gerrish",
    "Young",
    "Haddon",
    "Brunicardi",
    "Burns",
    "Goldhahn",
    "Saucedo",
    "Perkins",
    "Carr",
    "Jimenez",
    "Sharabi",
    "Quigley-Borg",
    "Barrilleaux",
    "Lucca",
    "Silva",
    "Williams",
    "Dailey",
    "Artha Negara",
    "Chertudi",
    "Pollack",
    "Sekona",
    "Goulding",
    "Lewis",
    "Nerenberg",
    "Mauricio",
    "Tulloch",
    "Yu",
    "Moss",
    "Yu",
    "Kalantar",
    "Pilar Moreno",
    "Weathersby",
    "Fahey",
    "Hough Jr",
    "Nadkarni",
    "Kuan",
    "Hasson",
    "Maoujoudi",
    "Swartout",
    "Fredricks",
    "Almassy",
    "Lund",
    "Anttila",
    "Lindo",
    "De Barra",
    "Weathersby",
    "Schubert",
    "Lopez-Thibodeaux",
    "Kalyanaraman",
    "Desai",
    "Amunrud",
    "Lopez",
    "Lee",
    "DeHaan",
    "Womack",
    "Aquino",
    "Bennett",
    "Fainshtein",
    "Fernandes",
    "Ullrich",
    "Eyestone",
    "Van Dongen",
    "Weeks",
    "Ferreira Da Silva",
    "Abolghasemi",
    "Schwarz",
    "Tramblee",
    "Lopez",
    "Pulido",
    "Cole",
    "Mallari Jr.",
    "White",
    "Moore",
    "Santos",
    "McClain",
    "Watts",
    "Adams",
    "Moore",
    "Campbell",
    "Ansari",
    "Kiesselbach",
    "Hui",
    "Barrientos",
    "Mojica Villegas",
    "Santizo",
    "Daus - Magbual",
    "Moon",
    "Lorenzana",
    "Hernandez",
    "Loi-On",
    "Villela-Smith",
    "Seals",
    "Elkan",
    "Martinez",
    "Daniel",
    "Bartoszynski",
    "Shelton",
    "Craddock",
    "Burns",
    "Nwamuo",
    "Sidman-Taveau",
    "Kaiser",
    "Pelletier",
    "Johnson",
    "Pacheco Matus",
    "Hwang",
    "Walsh",
    "Lim",
    "Abbas",
    "Gable",
    "Bianchi-Mcelwee",
    "Bunse",
    "Carey",
    "Schertle",
    "Scarabelli",
    "Kocak",
    "Burger",
    "Giambruno",
    "Gustlin",
    "Angle",
    "Lescure",
    "Avila",
    "Li",
    "Irigoyen II",
    "Howard",
    "McDonnell",
    "Wangsvick",
    "Mohammadi",
    "Harkness-Lee",
    "Elabed",
    "Braddock",
    "Gamble",
    "Thaning",
    "Ramirez",
    "Erike",
    "Wiese",
    "Duhe",
    "Stokke",
    "VonBleichert",
    "McCarthy",
    "Beltran",
    "Murcia-Cordoba",
    "Allen",
    "Stringer",
    "Wiese",
    "Lopez-Thibodeaux",
    "Moran",
    "Yadak",
    "Jazaeri",
    "Levine",
    "Noonan",
    "Raskin",
    "Mojtahedi",
    "McQuade",
    "Ubungen",
    "Nuila-Chae",
    "Sin",
    "Cross",
    "Heltzel",
    "Mills",
    "Terzakis",
    "De Andreis",
    "Sidman-Taveau",
    "Kim",
    "Honda",
    "Malavade",
    "Glanting",
    "Sandel",
    "Christensen",
    "Erpelo",
    "Belluomini",
    "Sanchez",
    "Kitamura",
    "Macaldo-Gubatina",
    "Stokes Cobb",
    "Lacy",
    "Weiss",
    "Richardson",
    "Kunkel",
    "De Barra",
    "Hunt",
    "Schubert",
    "Molteni",
    "Tovo",
    "Perez",
    "Wong",
    "Sidman-Taveau",
    "Tuttle",
    "Casale",
    "Ringseis",
    "Meyer",
    "Ledford",
    "Hughes",
    "Hagen",
    "Salceda",
    "Zuniga",
    "Rodriguez-Marquez",
    "Roper",
    "Thanos",
    "Takayama",
    "Dibble",
    "Gomez",
    "Hoffmann",
    "Rugani",
    "Hutchison",
    "Gu",
    "Dichiacchio",
    "Gallagher",
    "Mitchell",
    "Keyes",
    "Montano",
    "Lorenz",
    "Chertudi",
    "Begley",
    "Dailey",
    "Ulloa",
    "Mansel",
    "Ferguson",
    "McCarthy",
    "Jones",
    "Laux",
    "Moore",
    "Montgomery",
    "Thomas",
    "Lugo",
    "Tedone-Goldstone",
    "Kasser",
    "Tawakoli",
    "Cravy",
    "Montano-Rock",
    "Lopez-Thibodeaux",
    "Prochter",
    "Truglio",
    "Robbins",
    "Baird",
    "Roderick",
    "Constantino",
    "Ghiorso",
    "Fisher",
    "Young",
    "Smith",
    "Silva",
    "Barrilleaux",
    "Haddon",
    "Keyes",
    "Montano",
    "Lorenz",
    "Chertudi",
    "Begley",
    "Dailey",
    "Ulloa",
    "Mansel",
    "Ferguson",
    "Brunicardi",
    "Burns",
    "Goldhahn",
    "Saucedo",
    "Perkins",
    "Carr",
    "Jimenez",
    "Sharabi",
    "Quigley-Borg",
    "Lucca",
    "Williams",
    "Artha Negara",
    "Pollack",
    "Sekona",
    "Goulding",
    "Lewis",
    "Nerenberg",
    "Mauricio",
    "Tulloch"
];

function findElementByTextContent(parentElement, text) {
    const elements = parentElement.querySelectorAll(':contains(' + text + ')');
    return Array.from(elements).find(element => element.textContent.includes(text));
}

let foundProfessors = [];

// Assuming tables have the class name "table courses table-hover"
const tables = document.querySelectorAll('table.table.courses.table-hover');

tables.forEach((table, tableIndex) => {
    let tableRows = table.querySelectorAll('tr');

    for (let i = 1; i < tableRows.length; i++) {
        let tableRow = tableRows[i];

        let professor = tableRow.querySelector('td.hidden-xs:nth-child(7)');
        if (professor !== null) {
            let professorName = professor.textContent.trim();

            // Check if the current professor name is in the list of names to search
            if (namesToSearch.includes(professorName)) {
                foundProfessors.push(professorName);
            }
        }
    }
});

// Copy the array (create a new array with the same values)
let copiedProfessors = [...foundProfessors];

// Log the found professors
console.log("Found Professors:", foundProfessors);

// Log the copied professors
console.log("Copied Professors:", copiedProfessors);

