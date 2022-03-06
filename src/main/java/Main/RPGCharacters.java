package Main;

import BuildObjects.Armor;
import BuildObjects.BuildHero;
import BuildObjects.Hero;
import BuildObjects.Weapon;
import Display.DisplayArmor;
import Display.DisplayStats;
import Display.DisplayWeapons;
import EquipItem.EquipArmor;
import EquipItem.EquipWeapon;
import InvalidException.InvalidArmorException;
import InvalidException.InvalidWeaponException;
import Updates.DmgPerSecond;
import Updates.HeroLevelUp;
import Updates.UpdateStatsWithArmor;
import Updates.UpdateStatsWithWeapon;

import java.util.ArrayList;
import java.util.Scanner;

public class RPGCharacters {


	String[] choice;
	public Hero player;
	int charDPS;
	public ArrayList<Weapon> weaponslist = new ArrayList<Weapon>();
	public ArrayList<Armor> armorlist = new ArrayList<Armor>();
	BuildHero bh = new BuildHero();
	HeroLevelUp lu = new HeroLevelUp();
	DisplayWeapons dw = new DisplayWeapons();
	EquipWeapon ew = new EquipWeapon();
	DisplayArmor da = new DisplayArmor();
	EquipArmor ea = new EquipArmor();
	UpdateStatsWithArmor usa = new UpdateStatsWithArmor();
	DmgPerSecond dps = new DmgPerSecond();
	UpdateStatsWithWeapon usw = new UpdateStatsWithWeapon();
	DisplayStats ds = new DisplayStats();

	public static void main(String[] args) {

		RPGCharacters rpg = new RPGCharacters();
		rpg.Go();

	}

	//---Where the game loops
	public void Go() {

		while (true) {

			//---Prompt user for input, and split the input at ",",
			//---use the word at first index to initiate further functionality
			//---Enter -> start,your_name,your_class
			commandinput();

			//---it user types exit then exit while loop
			if (choice[0].trim().toLowerCase().equals("exit")) {
				System.out.println("Thank you for playing, goodbye.");
				break;
			}

			//---Building the hero
			//---third word decides which hero to build
			if (choice[0].trim().toLowerCase().equals("start")) {
				player = bh.buildHero(player, choice[1].toLowerCase().trim(), choice[2].toLowerCase().trim());
			}

			if (player != null) {

				//---level up hero
				//---adds to main attributes
				//---Four different level up scenarios, one for each class
				//---Enter -> level up
				if (choice[0].trim().toLowerCase().equals("level up")) {
					player = lu.levelUp(player);
					player = usa.updateStatsWithArmor(player);
				}

				//---Display available weapons
				//---Enter -> display weapons
				if (choice[0].trim().toLowerCase().equals("display weapons")) {
					dw.weaponsDisplay();
				}

				//---Equip weapons
				//---Enter -> equip weapon,weapon name
				if (choice[0].trim().toLowerCase().equals("equip weapon")) {
					try {
						ew.equipWeapon(player, choice[1].trim().toLowerCase());
						System.out.println("Hero equiped with " + player.getWeapon().getName());
					} catch (InvalidWeaponException e) {
						e.printStackTrace();
					}
				}

				//---Display available armor
				//---Enter -> display armor
				if (choice[0].trim().toLowerCase().equals("display armor")) {
					da.armorDisplay();
				}

				//---Equip armor
				//---armor can only be equiped at "Head", "Body" or "Legs"
				//---Enter equip armor,armor name,slot
				if (choice[0].trim().toLowerCase().equals("equip armor") && (choice[2].trim().toLowerCase().equals("head") || choice[2].trim().toLowerCase().equals("body") || choice[2].trim().toLowerCase().equals("legs"))) {
					try {
						//---armor is equiped
						ea.equipArmor(player, choice[1].trim().toLowerCase(), choice[2].trim().toLowerCase());
						System.out.println("Hero " + choice[2].trim().toLowerCase() + " equipped with " + player.getArmorName(choice[2].trim().toLowerCase()));
					} catch (InvalidArmorException e) {
						e.printStackTrace();
					}
					//---primary attributes are update by armor
					player = usa.updateStatsWithArmor(player);

					//---If correct slot name not typed, user is prompted
				} else if (choice[0].trim().toLowerCase().equals("equip armor") && (!choice[2].trim().toLowerCase().equals("head") || !choice[2].trim().toLowerCase().equals("body") || !choice[2].trim().toLowerCase().equals("legs"))) {
					System.out.println("You can only equip armor on Head, Body or Legs !");
				}

				//---damage per second calculated if there are no weapons equipped
				if (player.getWeapon() == null) {
					player = dps.damagePerSecondNoWeapon(player);
					//---damage per second calculated if weapons are equipped
				} else if (player.getWeapon() != null) {
					player = usw.updateStatsWithWeapon(player);
				}

				//---automatically display status at the end of each command
				ds.statsDisplay(player);

				//---Display status of the player
				//---Enter -> status
				if (choice[0].trim().toLowerCase().equals("status")) {
					ds.statsDisplay(player);
				}

				//---if no hero is created prompt user
			} else {
				System.out.println("To start choose your class: Warrior, Rogue, Ranger or Mage");
			}

			//---return to ask for input
			continue;
		}
	}


	//---prompts user for input and splits input at ","
	private void commandinput() {
		System.out.println("Enter your command : ");
		Scanner input = new Scanner(System.in);
		String Cstring = input.nextLine();
		choice = Cstring.split(",");
	}
}