package Updates;

import BuildObjects.Hero;

public class DmgPerSecond {

    //---calculate damage per second, when no weapon is equipped
    public Hero damagePerSecondNoWeapon(Hero player) {
        if (player.getChar().equals("warrior")) {
            double CharDPS = 1*(1+(player.getTotal_Strength()/100));
            player.setDPS(CharDPS);
        }
        else if (player.getChar().equals("rogue")) {
            double CharDPS = 1*(1+(player.getTotal_Dexterity()/100));
            player.setDPS(CharDPS);
        }
        else if (player.getChar().equals("ranger")) {
            double CharDPS = 1*(1+(player.getTotal_Dexterity()/100));
            player.setDPS(CharDPS);
        }
        else if (player.getChar().equals("mage")) {
            double CharDPS = 1*(1+(player.getTotal_Intelligence()/100));
            player.setDPS(CharDPS);
        }
        return player;
    }
}
