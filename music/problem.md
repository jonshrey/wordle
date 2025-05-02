You have a local music catalog

Every song has multiple artists, a music director, an album, a year of release, a language, and a genre

Every artist has a name, a year of birth, a gender, ?

Our music catalog is going to be represented in a fixed format in a text file

The goal of our program is to generate playlists of a particular genre/language/music director.

We'll store the song details in a database.

The first thing to decide will be the format of the file

It'll be a json file that looks like

[
    {
        "name": "Chaiyya Chaiyya",
        "artists": [
            {
                "name": "Sukhwinder Singh",
                "year_of_birth": 1900,
                "gender": "Male"
            },
            {
                "name": "Second Artist",
                "year_of_birth": 1900,
                "gender": "Female"
            }
        ],
        "music_director": {
            "name": "A R Rahman",
            "year_of_birth": 1900,
            "gender": "Male"
        },
        "album": {
            "name": "Dil Se",
            "year_of_release": 1900,
            "language": "Hindi"
        },
        "language": "Hindi",
        "genre": "awesome"
    },
]