What kinds of foreign-key relationships could be there?

* One-One relationships. For example, there is a User table whose job it is to store the username and the password digest. There is also a Profile table whose job it is to store the display name, profile picture, etc.

* One-Many relationships. For example, one user can have many phone numbers associated with her. There could be a User table which does not have any phone number column, and there could be a PhoneNumbers table which has a combination of userid and phonenumber.

For instance, user: id = 1, name = aruvi, password_digest = fjlkasjflas;kfd
phonenumbers: id = 1, userid = 1, phonenumber = 9876543210,
              id = 2, userid = 1, phonenumber = 9123456780

You can express this relationship both as "One user has multiple phone numbers associated with it" and as "One phone number has one user associated with it"

Hibernate lets you mark one of these associations as a "forward" relationship and the other as a "back" relationship

You express the forward relationship using the "OneToMany" or "ManyToOne" annotations without specifying anything, and you express a back relationship using the same annotations with a mappedBy = "instance variable name" parameter.

* Many-many relationships. For example, there could be many songs created by one artist and there could be many artists associated with one song. Both the directions are @ManyToMany and one of them should be marked as a backward relationship by using the mappedBy annotation.

In addition, the Hibernate Guide recommends as a good practice methods similar to the addArtist and removeArtist methods we wrote that keep both sides of this relationship in sync with each other.