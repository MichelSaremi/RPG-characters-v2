package EquipItems;

import BuildObjects.Hero;
import BuildObjects.MakeWeapons;
import BuildObjects.Weapon;
import InvalidExceptions.InvalidWeaponException;

public class EquipWeapon {

    MakeWeapons mw = new MakeWeapons();

    //---equip weapon if player class is valid and player level is valid
    public boolean equipWeapon(Hero player, String choice) throws InvalidWeaponException {

        for(Weapon item : mw.MakeWeapons()) {
            if (item.getName().equals(choice)) {
                if (!item.getChar().get(0).equals(player.getChar()) && !item.getChar().get(1).equals(player.getChar())) {

                    //---throw exception if weapon not applicable to class
                    throw new InvalidWeaponException("This weapon is not available to your class!");
                }
                else if (item.getRequired_level()>player.getLevel()) {

                    //---throw exception if weapon not applicable to level
                    throw new InvalidWeaponException("You dont have sufficient level to equip weapon!");
                }
                else {
                    //---otherwise equip weapon
                    player.setWeapon(item);
                    System.out.println("Hero equipped with " + player.getWeapon().getName());
                    return true;
                }
            }
        }
        //---prompt user if they type a wrong weapon name
        System.out.println("The weapon you have typed is not available! Type 'display weapons' to see what is available...");
        return false;
}}
