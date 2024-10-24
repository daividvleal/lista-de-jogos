package com.example.login_cadastro.util

import com.example.login_cadastro.model.Game
import com.example.login_cadastro.model.Person
import com.example.login_cadastro.model.User

object Sessao {

    var loged: Person? = null

    val people = mutableListOf(
        Person(User("email1@example.com", "pwd1"), "Name1", "123-456-7890", "Hobby1"),
        Person(User("email2@example.com", "pwd2"), "Name2", "987-654-3210", "Hobby2"),
        Person(User("email3@example.com", "pwd3"), "Name3", "555-123-4567", "Hobby3"),
        Person(User("email4@example.com", "pwd4"), "Name4", "111-222-3333", "Hobby4"),
        Person(User("email5@example.com", "pwd5"), "Name5", "444-555-6666", "Hobby5"),
        Person(User("email6@example.com", "pwd6"), "Name6", "777-888-9999", "Hobby6"),
        Person(User("email7@example.com", "pwd7"), "Name7", "222-333-4444", "Hobby7"),
        Person(User("email8@example.com", "pwd8"), "Name8", "888-999-0000", "Hobby8"),
        Person(User("email9@example.com", "pwd9"), "Name9", "333-444-5555", "Hobby9"),
        Person(User("email10@example.com", "pwd10"), "Name10", "999-000-1111", "Hobby10")
    )

    val games = listOf(
        Game("Super Mario Odyssey", 2017, "Nintendo Switch", "Platformer", false, false),
        Game("The Legend of Zelda: Breath of the Wild", 2017, "Nintendo Switch", "Action-adventure", false, false),
        Game("Red Dead Redemption 2", 2018, "PlayStation 4", "Action-adventure", true, true),
        Game("God of War", 2018, "PlayStation 4", "Action-adventure", false, false),
        Game("Grand Theft Auto V", 2013, "PlayStation 4", "Action-adventure", true, true),
        Game("The Witcher 3: Wild Hunt", 2015, "PlayStation 4", "Role-playing", false, false),
        Game("Minecraft", 2011, "PC", "Sandbox", true, true),
        Game("Fortnite", 2017, "PC", "Battle royale", true, true),
        Game("League of Legends", 2009, "PC", "MOBA", true, true),
        Game("Counter-Strike: Global Offensive", 2012, "PC", "First-person shooter", true, true),
        Game("Dota 2", 2013, "PC", "MOBA", true, true),
        Game("Overwatch", 2016, "PC", "First-person shooter", true, true),
        Game("Call of Duty: Modern Warfare", 2019, "PC", "First-person shooter", true, true),
        Game("Apex Legends", 2019, "PC", "Battle royale", true, true),
        Game("PUBG: Battlegrounds", 2017, "PC", "Battle royale", true, true),
        Game("Valorant", 2020, "PC", "First-person shooter", true, true),
        Game("Among Us", 2018, "PC", "Social deduction", true, true),
        Game("Genshin Impact", 2020, "PC", "Action role-playing", true, true),
        Game("Rocket League", 2015, "PC", "Sports", true, true),
        Game("Rainbow Six Siege", 2015, "PC", "First-person shooter", true, true),
        Game("Destiny 2", 2017, "PC", "First-person shooter", true, true),
        Game("The Elder Scrolls V: Skyrim", 2011, "PC", "Role-playing", false, false),
        Game("Grand Theft Auto: San Andreas", 2004, "PC", "Action-adventure", false, false),
        Game("Terraria", 2011, "PC", "Action-adventure", true, true),
        Game("Stardew Valley", 2016, "PC", "Simulation role-playing", true, true),
        Game("Team Fortress 2", 2007, "PC", "First-person shooter", true, true),
        Game("Portal 2", 2011, "PC", "Puzzle-platformer", false, false),
        Game("Half-Life 2", 2004, "PC", "First-person shooter", false, false),
        Game("The Sims 4", 2014, "PC", "Life simulation", false, false),
        Game("Civilization VI", 2016, "PC", "Turn-based strategy", true, true)
    )
    
}