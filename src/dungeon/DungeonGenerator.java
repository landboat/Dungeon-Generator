package dungeon;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;
import dnd.exceptions.NotProtectedException;
import dnd.exceptions.UnusualShapeException;
import dnd.models.ChamberShape;
import dnd.models.ChamberContents;
import dnd.models.Monster;
import dnd.models.Stairs;
import dnd.models.Trap;
import dnd.models.Treasure;
import dnd.models.Exit;

/**
 * Class is used to generate a dungeon or treasure to varying degrees of customization.
 */
public class DungeonGenerator {
    /**
     * Main method used to generate different user stories, based on user input.
     * @param args Is a string parameter that is not used at this time.
     */
    public static void main(String[] args) {
        int response = 0;
        boolean invalid = true;
        Scanner userSays = new Scanner(System.in);

        DungeonGenerator newDungeon = new DungeonGenerator();

        System.out.print("\n\n\nWelcome to my dungeon generator.\n\nWould you like to:\n"
                        + "1. Randomly generate your entire dungeon, or \n2. Build a "
                        + "dungeon with custom specs, or\n3. Just generate treasure, "
                        + "without a chamber?\n\nType 1, 2, or 3: ");

        while (invalid) {
            try {
                response = userSays.nextInt();
                userSays.nextLine();
                if (response != 1 && response != 2 && response != 3) {
                    System.out.print("\n\n\nInvalid number. Try again.\n\n"
                                    + "1. Randomly generate your entire dungeon, or \n"
                                    + "2. Build a dungeon with custom specs, or\n3. Just "
                                    + "generate treasure, without a chamber?\n\nType 1, 2, or 3: ");
                } else {
                    invalid = false;
                }
            } catch (InputMismatchException e) {
                System.out.print("\n\n\nNot a number. Try again.\n\n"
                                + "1. Randomly generate your entire dungeon, or \n"
                                + "2. Build a dungeon with custom specs, or\n3. Just "
                                + "generate treasure, without a chamber?\n\nType 1, 2, or 3: ");
                userSays.nextLine();
            }
        }

        // Creates a new ChamberShape object.
        ChamberShape newChamber = new ChamberShape();

        // Creates a new ChamberContents object and randomly populates it.
        ChamberContents newContents = new ChamberContents();

        // Passes method to create dungeon randomly or dynamically based on user input.
        if (response == 1) {
            newChamber = newDungeon.randomShape();

            // Constructs and fills an ArrayList to hold the list of exits in newChamber.
            ArrayList<Exit> exits = new ArrayList<Exit>();
            exits = newChamber.getExits();

            System.out.print("\n\nHere is the dungeon I randomly generated:\n\nChamber Shape: " + newChamber.getShape()
                            + ", Area : " + newChamber.getArea() + "\nNumber of exits: " + newChamber.getNumExits());
                for (int i = 0; i < newChamber.getNumExits(); i++) {
                    System.out.print("\nExit [" + (i + 1) + "] Location: " + exits.get(i).getLocation()
                                    + "\n         Direction: " + exits.get(i).getDirection());
                }
            System.out.println("\nContents: " + newContents.getDescription());
        } else if (response == 2) {
            newChamber = newDungeon.customShape();

            // Constructs and fills an ArrayList to hold the list of exits in newChamber.
            ArrayList<Exit> exits = new ArrayList<Exit>();
            exits = newChamber.getExits();

            newContents = newDungeon.customContents();
            if (newContents == null) {
                Monster monsters = new Monster();
                monsters = newDungeon.customMonsters();
                Stairs stairs = new Stairs();
                stairs = newDungeon.customStairs();
                Trap traps = new Trap();
                traps = newDungeon.customTraps();
                Treasure treasure = new Treasure();
                treasure = newDungeon.customTreasure();
                System.out.print("\n\nHere is the dungeon I generated:\n\nChamber Shape: " + newChamber.getShape()
                                + ", Area : " + newChamber.getArea() + "\nNumber of exits: " + newChamber.getNumExits());
                for (int i = 0; i < newChamber.getNumExits(); i++) {
                    System.out.print("\nExit [" + (i + 1) + "] Location: " + exits.get(i).getLocation()
                                    + "\n         Direction: " + exits.get(i).getDirection());
                }
                System.out.print("\nContents:\n\nMonsters: " + monsters.getMinNum() + " - " + monsters.getMaxNum() + " "
                                + monsters.getDescription() + "(s).\nStairs: " + stairs.getDescription() + ".\nTraps: "
                                + traps.getDescription() + ".\n" + "Treasure: " + treasure.getDescription()
                                + ",\nContained in: " + treasure.getContainer() + ",\nProtected by: ");
                try {
                    System.out.print(treasure.getProtection());
                } catch (NotProtectedException e) {
                    System.out.print("Absolutely nothing!!\n\n");
                }
            } else {
                System.out.print("\n\nHere is the dungeon I generated:\n\nChamber Shape: " + newChamber.getShape()
                                + ", Area : " + newChamber.getArea() + "\nNumber of exits: " + newChamber.getNumExits());
                for (int i = 0; i < newChamber.getNumExits(); i++) {
                    System.out.print("\nExit [" + (i + 1) + "] Location: " + exits.get(i).getLocation()
                                    + "\n         Direction: " + exits.get(i).getDirection());
                }
                System.out.println("\nContents: " + newContents.getDescription());
            }
        } else if (response == 3) {
                Treasure treasure = new Treasure();
                treasure = newDungeon.justTreasure();
                System.out.print("\n\nHere is the treasure I generated:\n\nTreasure: " + treasure.getDescription()
                                + ",\nContained in: " + treasure.getContainer() + ",\nProtected by: ");
                try {
                    System.out.print(treasure.getProtection());
                } catch (NotProtectedException e) {
                    System.out.print("Absolutely nothing!!\n\n");
                }
            }

        System.out.println("\n");

        userSays.close();

    }

    /**
     * Method used to randomly generate the chamber shape and number of exits.
     * @return The ChamberShape object once its data has been randomly generated.
     */
    public ChamberShape randomShape() {

        // Constructs a new ChamberShape object called chamber.
        ChamberShape chamber = new ChamberShape();

        // Randomly sets chamber's geometry and number of exits.
        chamber.setShape();
        chamber.setNumExits();
        return chamber;
    }

    /**
     * Method used to create a chamber with a custom shape, as determined from the user's input.
     * @return The ChamberShape object once its data has been generated.
     */
    public ChamberShape customShape() {
        Scanner userSays = new Scanner(System.in);
        boolean invalid = true;
        int response = 0;

        System.out.print("\nAh, an expert dungeon designer... let's see what you come up with!\n\n"
                        + "At each step along the way, you can still\nchoose to randomize the selection "
                        + "if you'd like.\n\nFirst, let's design your chamber's shape.\n\nEither enter your "
                        + "D20 roll (between 1-20), or\ntype 0 and I will randomly choose for you: ");

        ChamberShape chamber = new ChamberShape();

        while (invalid) {
            try {
                response = userSays.nextInt();
                userSays.nextLine();
                if (response < 0 || response > 20) {
                    System.out.print("\n\n\nInvalid number. Try again.\n\n"
                                    + "Either enter your D20 roll (between 1-20), or\n"
                                    + "type 0 and I will randomly choose for you: ");
                } else {
                    invalid = false;
                }
            } catch (InputMismatchException e) {
                System.out.print("\n\n\nNot a number. Try again.\n\n"
                                + "Either enter your D20 roll (between 1-20), or\n"
                                + "type 0 and I will randomly choose for you: ");
                userSays.nextLine();
            }
        }

        if (response == 0) {
            chamber.setShape();
        } else {
            chamber.setShape(response);
        }

        System.out.print("\nYou have made a " + chamber.getShape() + " chamber, with an\n"
                        + "area of " + chamber.getArea() + " square feet - Nice choice!\n\n"
                        + "Now let's choose the number of exits in this chamber:\n\nEither enter "
                        + "your D20 roll (between 1-20), or\ntype 0 and I will randomly choose for you: ");
        invalid = true;
        while (invalid) {
            try {
                response = userSays.nextInt();
                userSays.nextLine();
                if (response < 0 || response > 20) {
                    System.out.print("\n\n\nInvalid number. Try again.\n\n"
                                    + "Either enter your D20 roll (between 1-20), or\n"
                                    + "type 0 and I will randomly choose for you: ");
                } else {
                    invalid = false;
                }
            } catch (InputMismatchException e) {
                System.out.print("\n\n\nNot a number. Try again.\n\n"
                                + "Either enter your D20 roll (between 1-20), or\n"
                                + "type 0 and I will randomly choose for you: ");
                userSays.nextLine();
            }
        }
        if (response == 0) {
            chamber.setNumExits();
        } else {
            chamber.setNumExits(response);
        }
        System.out.print("\nYour chamber now has " + chamber.getNumExits() + " exit(s).\n");
        return chamber;
    }

    /**
     * Method used to choose between 3 contents-generating options; completely
     * random, custom based off a single roll, or completely custom.
     * @return The ChamberContents object once its data has been generated.
     */
    public ChamberContents customContents() {
        Scanner userSays = new Scanner(System.in);
        boolean invalid = true;
        int response = 0;

        ChamberContents contents = new ChamberContents();

        System.out.print("\nNext, we will set the contents for the chamber:\n\n");

        while (invalid) {
            try {
                System.out.print("Would you like to:\n1. Randomly generate the contents of your dungeon,\n"
                                + "2. Provide a D20 roll to set the contents of your dungeon, or\n"
                                + "3. Create custom contents step by step?\nType 1, 2, or 3: ");
                response = userSays.nextInt();
                userSays.nextLine();
                if (response != 1 && response != 2 && response != 3) {
                    System.out.print("\n\n\nInvalid number. Try again.\n\n");
                } else {
                    invalid = false;
                }
            } catch (InputMismatchException e) {
                System.out.print("\n\n\nNot a number. Try again.\n\n");
                userSays.nextLine();
            }
        }

        if (response == 1) {
            contents.setDescription();
        } else if (response == 2) {
            System.out.print("Enter your D20 roll: ");
            response = userSays.nextInt();
            contents.setDescription(response);
        } else if (response == 3) {
            System.out.print("\nSo you want to do this the hard way... Good for you.\n\n"
                            + "You will now need to roll seperately to determine if any\n"
                            + "monsters, stairs, traps, or treasures will be generated.\n\n");
            return null;
        }

        return contents;
    }

    /**
     * Method used to generate type and number of monsters based off of the user's input.
     * @return The Monsters object once its data has been generated.
     */
    public Monster customMonsters() {
        Scanner userSays = new Scanner(System.in);
        boolean invalid = true;
        int response = 0;

        Monster monsters = new Monster();

        System.out.print("\nLet's start with monsters.\n\nEither enter a number from 1-100"
                        + ", or\ntype 0 and I will randomly choose for you: ");

        while (invalid) {
            try {
                response = userSays.nextInt();
                userSays.nextLine();
                if (response < 0 || response > 100) {
                    System.out.print("\n\n\nInvalid number. Try again.\n\n"
                                    + "Either enter a number from 1-100, or\n"
                                    + "type 0 and I will randomly choose for you: ");
                } else {
                    invalid = false;
                }
            } catch (InputMismatchException e) {
                System.out.print("\n\n\nNot a number. Try again.\n\n"
                                + "Either enter a number from 1-100, or\n"
                                + "type 0 and I will randomly choose for you: ");
                userSays.nextLine();
            }
        }
        if (response == 0) {
            monsters.setType();
        } else {
            monsters.setType(response);
        }
        System.out.print("\nYour chamber now contains " + monsters.getMinNum() + " - "
                        + monsters.getMaxNum() + " " + monsters.getDescription() + "(s).\n");
        return monsters;
    }

    /**
     * Method used to generate the chamber's stairs based off of the user's input.
     * @return The Stairs object once its data has been generated.
     */
    public Stairs customStairs() {
        Scanner userSays = new Scanner(System.in);
        boolean invalid = true;
        int response = 0;

        Stairs stairs = new Stairs();

        System.out.print("\nNext, we will determine if any stairs exist in your dungeon.\n\n"
                        + "Either enter your D20 roll (between 1-20), or\ntype 0 and "
                        + "I will randomly choose for you: ");

        while (invalid) {
            try {
                response = userSays.nextInt();
                userSays.nextLine();
                if (response < 0 || response > 20) {
                    System.out.print("\n\n\nInvalid number. Try again.\n\n"
                                    + "Either enter your D20 roll (between 1-20), or\n"
                                    + "type 0 and I will randomly choose for you: ");
                } else {
                    invalid = false;
                }
            } catch (InputMismatchException e) {
                System.out.print("\n\n\nNot a number. Try again.\n\n"
                                + "Either enter your D20 roll (between 1-20), or\n"
                                + "type 0 and I will randomly choose for you: ");
                userSays.nextLine();
            }
        }
        if (response == 0) {
            stairs.setType();
        } else {
            stairs.setType(response);
        }
        return stairs;
    }

    /**
     * Method used to generate traps based off of the user's input.
     * @return The Traps object once its data has been generated.
     */
    public Trap customTraps() {
        Scanner userSays = new Scanner(System.in);
        boolean invalid = true;
        int response = 0;

        Trap traps = new Trap();

        System.out.print("\nAlmost there!\nNext, we will determine if any traps exist in your dungeon.\n\n"
                        + "Either enter your D20 roll (between 1-20), or\ntype 0 and "
                        + "I will randomly choose for you: ");

        while (invalid) {
            try {
                response = userSays.nextInt();
                userSays.nextLine();
                if (response < 0 || response > 20) {
                    System.out.print("\n\n\nInvalid number. Try again.\n\n"
                                    + "Either enter your D20 roll (between 1-20), or\n"
                                    + "type 0 and I will randomly choose for you: ");
                } else {
                    invalid = false;
                }
            } catch (InputMismatchException e) {
                System.out.print("\n\n\nNot a number. Try again.\n\n"
                                + "Either enter your D20 roll (between 1-20), or\n"
                                + "type 0 and I will randomly choose for you: ");
                userSays.nextLine();
            }
        }
        if (response == 0) {
            traps.setDescription();
        } else {
            traps.setDescription(response);
        }
        return traps;
    }

    /**
     * Method used to generate treasure, as well as its container, and protection
     * based off of the user's input.
     * @return The Treasure object once its data has been generated.
     */
    public Treasure customTreasure() {
        Scanner userSays = new Scanner(System.in);
        boolean invalid = true;
        int response = 0;

        Treasure treasure = new Treasure();

        System.out.print("\nFinally, we will set any treasure held within the chamber.\n\nEither enter "
                        + "a number from 1-100, or\ntype 0 and I will randomly choose for you: ");

        while (invalid) {
            try {
                response = userSays.nextInt();
                userSays.nextLine();
                if (response < 0 || response > 100) {
                    System.out.print("\n\n\nInvalid number. Try again.\n\n"
                                    + "Either enter a number from 1-100, or\n"
                                    + "type 0 and I will randomly choose for you: ");
                } else {
                    invalid = false;
                }
            } catch (InputMismatchException e) {
                System.out.print("\n\n\nNot a number. Try again.\n\n"
                                + "Either enter a number from 1-100, or\n"
                                + "type 0 and I will randomly choose for you: ");
                userSays.nextLine();
            }
        }
        if (response == 0) {
            treasure.setDescription();
        } else {
            treasure.setDescription(response);
        }

        System.out.print("\nYour chamber now contains a prize of " + treasure.getDescription() + ".\n\n"
                        + "Now you need to roll to decide how store this treasure.\n\nEither enter your "
                        + "D20 roll (between 1-20), or\ntype 0 and I will randomly choose for you: ");
        invalid = true;
        while (invalid) {
            try {
                response = userSays.nextInt();
                userSays.nextLine();
                if (response < 0 || response > 20) {
                    System.out.print("\n\n\nInvalid number. Try again.\n\n"
                                    + "Either enter your D20 roll (between 1-20), or\n"
                                    + "type 0 and I will randomly choose for you: ");
                } else {
                    invalid = false;
                }
            } catch (InputMismatchException e) {
                System.out.print("\n\n\nNot a number. Try again.\n\n"
                                + "Either enter your D20 roll (between 1-20), or\n"
                                + "type 0 and I will randomly choose for you: ");
                userSays.nextLine();
            }
        }
        if (response == 0) {
            treasure.setContainer();
        } else {
            treasure.setContainer(response);
        }
        return treasure;
    }

    /**
     * Method used to generate treasure/container/protection based off of the user's input, without generating a chamber.
     * @return The Treasure object once its data has been generated.
     */
    public Treasure justTreasure() {
        Scanner userSays = new Scanner(System.in);
        boolean invalid = true;
        int response = 0;

        Treasure treasure = new Treasure();

        System.out.print("\nSo, you want to generate treasure and nothing more - Pitter patter.\n\nEither "
                        + "enter a number from 1-100, or\ntype 0 and I will randomly choose for you: ");

        while (invalid) {
            try {
                response = userSays.nextInt();
                userSays.nextLine();
                if (response < 0 || response > 100) {
                    System.out.print("\n\n\nInvalid number. Try again.\n\n"
                                    + "Either enter a number from 1-100, or\n"
                                    + "type 0 and I will randomly choose for you: ");
                } else {
                    invalid = false;
                }
            } catch (InputMismatchException e) {
                System.out.print("\n\n\nNot a number. Try again.\n\n"
                                + "Either enter a number from 1-100, or\n"
                                + "type 0 and I will randomly choose for you: ");
                userSays.nextLine();
            }
        }
        if (response == 0) {
            treasure.setDescription();
        } else {
            treasure.setDescription(response);
        }

        System.out.print("\nYou have generated a prize of " + treasure.getDescription() + ".\n\n"
                        + "Now you need to roll to decide how store this treasure.\n\nEither enter your "
                        + "D20 roll (between 1-20), or\ntype 0 and I will randomly choose for you: ");
        invalid = true;
        while (invalid) {
            try {
                response = userSays.nextInt();
                userSays.nextLine();
                if (response < 0 || response > 20) {
                    System.out.print("\n\n\nInvalid number. Try again.\n\n"
                                    + "Either enter your D20 roll (between 1-20), or\n"
                                    + "type 0 and I will randomly choose for you: ");
                } else {
                    invalid = false;
                }
            } catch (InputMismatchException e) {
                System.out.print("\n\n\nNot a number. Try again.\n\n"
                                + "Either enter your D20 roll (between 1-20), or\n"
                                + "type 0 and I will randomly choose for you: ");
                userSays.nextLine();
            }
        }
        if (response == 0) {
            treasure.setContainer();
        } else {
            treasure.setContainer(response);
        }
        return treasure;
    }
}
