What is the OOP equivalent of an RDBMS Table?
Class / Type

What about an RDBMS Column?
instance variables / properties / members / attributes

What is the equivalent of a Row?
objects.

There is an equivalency between thinking of a Table of Rows and a List of Objects.

But it is not perfect.

There are frameworks that help... These are called Object-Relational Modelers.

YOu want to write code that looks like

//Player p = DB.find(Player.class, "Dhoni");

Or like

//Player p = new Player();
// p.setName("Dhoni");
// p.setTeam(...)
// p.setOtherTHings()
// DB.persist(p);

What is the equivalent of a Foreign Key relationship?
Association/composition

You want to write code that looks like

class Player {
    private String name;
    //other properties
    private Team team;
}




----------------------------------------------------
Many-Many relationships demand a join table!

Player
id
name
other nonsense

Player_FantasyTeam
playerid (FK to Player.id)
fantasyteamid (FK TO FantasyTeam.id)

FantasyTeam
id
name
other nonsense