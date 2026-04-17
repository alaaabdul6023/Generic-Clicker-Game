import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.Font;

public class ClickerGame {
    static int coins = 150000;
    static double upgradeCost = 100;
    static double luckinessCost = 1000;
    static double autoClickerCost = 3000;
    static int clickPower = 1;
    static double autoClickerSpeedUpgradeCost = 750;
    static double autoClickerPowerUpgradeCost = 750;
    static double gemsLuckyUpgradeCost = 5;
    static int autoClicker = 0;
    static int luckiness = 20;
    static int lucky = 0;
    static int autoClickerPower = 1;
    static int autoClickerSpeed = 1500;
    static int Rebirths = 2;
    static int RebirthCost = 100000;
    static double n = 1.3;
    static double g = 1.5;
    static int gems = 10000;
    static int gemsChance = 0;
    static int gemsChanceUpgrade = 50;
    static int gemsMaxLuckyUpgrade = 0;
    static int gemsAutoClickerSpeed = 3000;
    static int gemsAutoClickerPower = 1;
    static int gemAutoClicker = 0;
    static int gemAutoClickerCost = 200;
    static int gemsAutoClickerSpeedUpgradeCost = 10;
    static int gemsAutoClickerPowerUpgradeCost = 10;
    static ClickerGameAudio audio = new ClickerGameAudio();
    static ClickerPlaylist playlist = new ClickerPlaylist();
    public static void main(String[] args) {
        playlist.playRandomSong();
        JLabel coinLabel = new JLabel("   Coins: " + coins);
        coinLabel.setFont(new Font("Arial", Font.BOLD, 28));
        JLabel gemsLabel = new JLabel(" Gems: " + gems);
        gemsLabel.setFont(new Font("Arial", Font.BOLD, 28));
        JLabel RebirthsLabel = new JLabel(" Rebirths: " + Rebirths);
        RebirthsLabel.setFont(new Font("Arial", Font.BOLD, 28));
        JFrame frame = new JFrame("Clicker Game!!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout()); 
        JButton workBtn = new JButton("Work ( +" + clickPower + " coins)");
        workBtn.setPreferredSize(new Dimension(1850, 750));
        JButton openCoinsShopBtn = new JButton("Open Coins Shop");
        JButton openGemsShopBtn = new JButton("Open Gems Shop");
        JButton upgradeBtn = new JButton("Gain +1 Click (Cost: 100 Coins)");
        JButton luckyupgradeBtn = new JButton("Gain +1 Luck (Cost: 1000 Coins)");
        JButton autoClickerBuyBtn = new JButton("Gain +1 coin autoclicker (Cost: 3000 coins)");
        JButton autoClickerSpeedUpgradeBtn = new JButton("AutoClicker Speed Upgrade (Cost: " + (int)autoClickerSpeedUpgradeCost + ")");
        JButton autoClickerPowerUpgradeBtn = new JButton("AutoClicker Power Upgrade (Cost: " + (int)autoClickerPowerUpgradeCost + ")");
        JButton gemsLuckyUpgradeBtn = new JButton("Upgrade Gems Chance (Cost: " + (int)gemsLuckyUpgradeCost + " gems)");
        JButton RebirthBtn = new JButton("Gain +1 Rebirths (Cost: 100,000 Coins)");
        JButton gemsAutoClickerBuyBtn = new JButton("Gain +1 gem autoclicker (Cost: 200 gems)");
        JButton gemsAutoClickerPowerUpgradeBtn = new JButton("Upgrade Gems AutoClicker Power (Cost: " + (int)gemsAutoClickerPowerUpgradeCost + ")");
        JButton gemsAutoClickerSpeedUpgradeBtn = new JButton("Upgrade Gems AutoClicker Speed (Cost: " + (int)gemsAutoClickerSpeedUpgradeCost + ")");
        JButton gem1TradeInBtn = new JButton("Trade 1 Gem for 50 Coins!");
        JButton gem10TradeInBtn = new JButton("Trade 10 Gems for 500 Coins!");
        JButton gem100TradeInBtn = new JButton("Trade 100 Gems for 5000 Coins!");
        JButton gem1000TradeInBtn = new JButton("Trade 1000 Gems for 50000 Coins!");
        JButton endingBtn = new JButton("To Win You Need: 150,000 Coins, 10,000 Gems, and 2 Rebirths");
        JButton playAgainBtn = new JButton("Click to continue playing");
        JButton exitBtn = new JButton("Click to exit!");
        workBtn.setBackground(Color.GRAY);
        workBtn.setForeground(Color.WHITE);
        workBtn.setOpaque(true);
        Timer engine = new Timer(autoClickerSpeed, k -> {
            if (autoClicker > 0) {
                coins += (autoClicker * autoClickerPower);
                coinLabel.setText("   Coins: " + coins);
            }
        });

        Timer engine2 = new Timer(gemsAutoClickerSpeed, k -> {
            if (gemAutoClicker > 0) {
                gems += (gemAutoClicker * gemsAutoClickerPower);
                gemsLabel.setText("   Gems: " + gems);
            }
        });
        // 1. EARN MONEY
        workBtn.addActionListener(e -> {
            lucky = 1 + (int)(Math.random() * luckiness);
            if (lucky == 1) {
                coins += clickPower * 2;
            } else {
                coins += clickPower;
            }
            coinLabel.setText("   Coins: " + coins);
            gemsChance++;
            if(gemsChance >= gemsChanceUpgrade)
            {
                gems ++;
                gemsLabel.setText("   Gems: " + gems);
                gemsChance = 0;
            }
        });
        
        // 2. Click Upgrade
        upgradeBtn.addActionListener(e -> {
            if (clickPower < 100) {
                if (coins >= upgradeCost) {
                    coins -= upgradeCost;
                    upgradeCost *= n;
                    clickPower += 1;
                    coinLabel.setText("   Coins: " + coins);
                    upgradeBtn.setText("Upgrade Click (Cost: " + (int)upgradeCost + ")");
                    workBtn.setText("Work ( +" + clickPower + " coins)");
                } else {
                    upgradeBtn.setText("Not enough coins!");
                    Timer t = new Timer(1000, f -> upgradeBtn.setText("Gain +1 Click (Cost: " + (int)upgradeCost + ")"));
                    t.setRepeats(false);
                    t.start();
                }
            } else {
                upgradeBtn.setText("Max Upgrades Reached!");
            }
        });
        
        // 3. Luck Upgrade
        luckyupgradeBtn.addActionListener(e -> {
            if (luckiness > 5) {
                if (coins >= luckinessCost) {
                    coins -= luckinessCost;
                    luckinessCost *= n;
                    luckiness -= 1;
                    coinLabel.setText("   Coins: " + coins);
                    luckyupgradeBtn.setText("Luck +1 (Cost: " + (int)luckinessCost + ")");
                } else {
                    luckyupgradeBtn.setText("Not enough coins!");
                    Timer t = new Timer(1000, f -> luckyupgradeBtn.setText("Luck +1 (Cost: " + (int)luckinessCost + ")"));
                    t.setRepeats(false);
                    t.start();
                }
            }
            else
            {
                luckyupgradeBtn.setText("Max Upgrades Reached!");
            }
        });
        
        // 4. Buy Autoclicker
        autoClickerBuyBtn.addActionListener(e -> {
            if (autoClicker < 6){
                if (coins >= autoClickerCost) {
                coins -= autoClickerCost;
                autoClickerCost *= g;
                autoClicker += 1;
                coinLabel.setText("   Coins: " + coins);
                autoClickerBuyBtn.setText("AutoClicker (Cost: " + (int)autoClickerCost + ")");
                } else {
                autoClickerBuyBtn.setText("Not enough coins!");
                Timer t = new Timer(1000, f -> autoClickerBuyBtn.setText("AutoClicker (Cost: " + (int)autoClickerCost + ")"));
                t.setRepeats(false);
                t.start();
                }
            }
            else
            {
                autoClickerBuyBtn.setText("Max Upgrades Reached!");    
            }
        });
        
        autoClickerPowerUpgradeBtn.addActionListener(j -> {
            if (autoClickerPower < 20) {
                if (coins >= autoClickerPowerUpgradeCost) {
                coins -= autoClickerPowerUpgradeCost;
                autoClickerPowerUpgradeCost *= n;
                coinLabel.setText("   Coins: " + coins);
                autoClickerPowerUpgradeBtn.setText("AutoClicker Power Upgrade (Cost: " + (int)autoClickerPowerUpgradeCost + "coins)");
                if (autoClickerPower <= 1)
                {
                    autoClickerPower += 4;
                    autoClickerPowerUpgradeBtn.setText("AutoClicker Power Upgrade (Cost: " + (int)autoClickerPowerUpgradeCost + "coins)");
                }
                
                else 
                {
                    autoClickerPower += 5;
                    autoClickerPowerUpgradeBtn.setText("AutoClicker Power Upgrade (Cost: " + (int)autoClickerPowerUpgradeCost + "coins)");
                }
                }   else {
                    autoClickerPowerUpgradeBtn.setText("Not enough coins!");
                    Timer t = new Timer(1000, f -> autoClickerPowerUpgradeBtn.setText("AutoClicker Power Upgrade (Cost: " + (int)autoClickerPowerUpgradeCost + "coins)"));
                    t.setRepeats(false);
                    t.start();
                }
            }
            else
            {
                autoClickerPowerUpgradeBtn.setText("Max Upgrades Reached!");
            }
        });

        autoClickerSpeedUpgradeBtn.addActionListener(e -> {
            if (autoClickerSpeed > 250){
                if (coins >= autoClickerSpeedUpgradeCost) {
                    coins -= autoClickerSpeedUpgradeCost;
                    autoClickerSpeedUpgradeCost *= n;
                    coinLabel.setText("   Coins: " + coins);
                    autoClickerSpeedUpgradeBtn.setText("AutoClicker Speed Upgrade (Cost: " + (int)autoClickerSpeedUpgradeCost + "coins)");
                    autoClickerSpeed -= 250;
                    engine.setDelay(autoClickerSpeed);
                    engine.restart();
                } else {
                    autoClickerSpeedUpgradeBtn.setText("Not enough coins!");
                    Timer t = new Timer(1000, f -> autoClickerSpeedUpgradeBtn.setText("AutoClicker Speed Upgrade (Cost: " + (int)autoClickerSpeedUpgradeCost + "coins)"));
                    t.setRepeats(false);
                    t.start();
                }
            }
            else
            {
                autoClickerSpeedUpgradeBtn.setText("Max Upgrades Reached!");
            }
        });
        
        gemsLuckyUpgradeBtn.addActionListener(e -> {
            if(gemsMaxLuckyUpgrade < 10)
            {
                if (gems >= gemsLuckyUpgradeCost) {
                    gems -= gemsLuckyUpgradeCost;
                    gemsLuckyUpgradeCost *= 1.4;
                    gemsChanceUpgrade -=5;
                    gemsMaxLuckyUpgrade ++;
                    gemsLabel.setText("   Gems: " + gems);
                    gemsLuckyUpgradeBtn.setText("Upgrade Gems Chance (Cost: " + (int)gemsLuckyUpgradeCost + " gems)");
                } else {
                    gemsLuckyUpgradeBtn.setText("Not enough gems!");
                    Timer t = new Timer(1000, f -> gemsLuckyUpgradeBtn.setText("Upgrade Gems Chance (Cost: " + (int)gemsLuckyUpgradeCost + " gems)"));
                    t.setRepeats(false);
                    t.start();
                }
            }
            else
            {
                gemsLuckyUpgradeBtn.setText("Max Upgrades Reached!");
            }
        });
        
        gemsAutoClickerBuyBtn.addActionListener(e -> {
            if (gemAutoClicker < 6){
                if (gems >= gemAutoClickerCost) {
                gems -= gemAutoClickerCost;
                gemAutoClickerCost *= g;
                gemAutoClicker ++;
                gemsLabel.setText("   Gems: " + gems);
                gemsAutoClickerBuyBtn.setText("Buy +1 Gem AutoClicker (Cost: " + (int)gemAutoClickerCost + " gems)");
                } else {
                gemsAutoClickerBuyBtn.setText("Not enough gems!");
                Timer t = new Timer(1000, f -> gemsAutoClickerBuyBtn.setText("Buy +1 Gem AutoClicker (Cost: " + (int)gemAutoClickerCost + " gems)"));
                t.setRepeats(false);
                t.start();
                }
            }
            else
            {
                gemsAutoClickerBuyBtn.setText("Max Upgrades Reached!");    
            }
        });
        
        gemsAutoClickerSpeedUpgradeBtn.addActionListener(j -> {
            if (gemsAutoClickerSpeed > 300) {
                if (gems >= gemsAutoClickerSpeedUpgradeCost) {
                gems -= gemsAutoClickerSpeedUpgradeCost;
                gemsAutoClickerSpeedUpgradeCost *= 1.5;
                gemsAutoClickerSpeed -= 300;
                engine2.setDelay(gemsAutoClickerSpeed);
                engine2.restart();
                gemsLabel.setText("   Gems: " + gems);
                gemsAutoClickerSpeedUpgradeBtn.setText("Upgrade Gems AutoClicker Speed (Cost: " + (int)gemsAutoClickerSpeedUpgradeCost + ")");
                }   else {
                    gemsAutoClickerSpeedUpgradeBtn.setText("Not enough gems!");
                    Timer t = new Timer(1000, f -> gemsAutoClickerSpeedUpgradeBtn.setText("Upgrade Gems AutoClicker Speed (Cost: " + (int)gemsAutoClickerSpeedUpgradeCost + ")"));
                    t.setRepeats(false);
                    t.start();
                }
            }
            else
            {
                gemsAutoClickerSpeedUpgradeBtn.setText("Max Upgrades Reached!");
            }
        });
        
        gemsAutoClickerPowerUpgradeBtn.addActionListener(j -> {
            if (gemsAutoClickerPower < 10) {
                if (gems >= gemsAutoClickerPowerUpgradeCost) {
                gems -= gemsAutoClickerPowerUpgradeCost;
                gemsAutoClickerPowerUpgradeCost *= 1.5;
                gemsLabel.setText("   Gems: " + gems);
                gemsAutoClickerPowerUpgradeBtn.setText("Gems AutoClicker Power Upgrade (Cost: " + (int)gemsAutoClickerPowerUpgradeCost + " gems)");
                gemsAutoClickerPower ++;
                }   else {
                    gemsAutoClickerPowerUpgradeBtn.setText("Not enough gems!");
                    Timer t = new Timer(1000, f -> gemsAutoClickerPowerUpgradeBtn.setText("Gems AutoClicker Power Upgrade (Cost: " + (int)gemsAutoClickerPowerUpgradeCost + " gems)"));
                    t.setRepeats(false);
                    t.start();
                }
            }
            else
            {
                gemsAutoClickerPowerUpgradeBtn.setText("Max Upgrades Reached!");
            }
        });
        
        gem1TradeInBtn.addActionListener(t -> {
            if (gems >= 1)
            {
                gems--;
                coins+=50;
                coinLabel.setText("   Coins: " + coins);
                gemsLabel.setText("   Gems: " + gems);
            }
            else
            {
                gem1TradeInBtn.setText("Not enough gems");
                Timer g = new Timer(1000, f -> gem1TradeInBtn.setText("Trade 1 gems for 50 coins"));
                g.setRepeats(false);
                g.start();
            }
        });
        
        gem10TradeInBtn.addActionListener(t -> {
            if (gems >= 10)
            {
                gems-=10;
                coins+=500;
                coinLabel.setText("   Coins: " + coins);
                gemsLabel.setText("   Gems: " + gems);
            }
            else
            {
                gem10TradeInBtn.setText("Not enough gems");
                Timer g = new Timer(1000, f -> gem10TradeInBtn.setText("Trade 10 gems for 500 coins"));
                g.setRepeats(false);
                g.start();
            }
        });
        
        gem100TradeInBtn.addActionListener(t -> {
            if (gems >= 100)
            {
                gems-=100;
                coins+=5000;
                coinLabel.setText("   Coins: " + coins);
                gemsLabel.setText("   Gems: " + gems);
            }
            else
            {
                gem100TradeInBtn.setText("Not enough gems");
                Timer g = new Timer(1000, f -> gem100TradeInBtn.setText("Trade 100 gems for 5000 coins"));
                g.setRepeats(false);
                g.start();
            }
        });
        
        gem1000TradeInBtn.addActionListener(t -> {
            if (gems >= 1000)
            {
                gems-=1000;
                coins+=50000;
                coinLabel.setText("   Coins: " + coins);
                gemsLabel.setText("   Gems: " + gems);
            }
            else
            {
                gem1000TradeInBtn.setText("Not enough gems");
                Timer g = new Timer(1000, f -> gem1000TradeInBtn.setText("Trade 1000 gems for 50000 coins"));
                g.setRepeats(false);
                g.start();
            }
        });
        
        RebirthBtn.addActionListener(t -> {
            if (coins >= RebirthCost){
                audio.playrebirthSound();
                Rebirths +=1;
                coins = 0;
                coinLabel.setText("   Coins: " + coins);
                RebirthCost *= 1.75;
                RebirthBtn.setText("Rebirth (Cost: " + (int)RebirthCost + ")");
                upgradeCost = 100; upgradeBtn.setText("Upgrade Click (Cost: " + (int)upgradeCost + ")");
                luckinessCost = 1000; luckyupgradeBtn.setText("Luck +1 (Cost: " + (int)luckinessCost + ")");
                autoClickerCost = 3000; autoClickerBuyBtn.setVisible(true); autoClickerBuyBtn.setText("AutoClicker (Cost: " + (int)autoClickerCost + ")");
                clickPower = 1+Rebirths; workBtn.setText("Work ( +" + clickPower + " coins)");
                autoClickerSpeedUpgradeCost = 500; autoClickerSpeedUpgradeBtn.setText("AutoClicker Speed Upgrade (Cost: " + (int)autoClickerSpeedUpgradeCost + ")");
                autoClickerPowerUpgradeCost = 500; autoClickerPowerUpgradeBtn.setText("AutoClicker Power Upgrade (Cost: " + (int)autoClickerPowerUpgradeCost + ")");
                autoClicker = 0; 
                luckiness = 20; 
                lucky = 0;
                autoClickerPower = 1;
                autoClickerSpeed = 2000;
                JOptionPane.showMessageDialog(frame, "🏆 Youve rebirthed " + Rebirths + " times!");
                n -=.15;
                g -= .125;
            }
        });
        
        openCoinsShopBtn.addActionListener(e -> {
            JDialog coinsShopWindow = new JDialog(frame, "Upgrade Shop", true); 
            coinsShopWindow.setLayout(new GridLayout(0, 1, 10, 10));
            coinsShopWindow.setSize(300, 500);
            coinsShopWindow.setLocationRelativeTo(frame);
            coinsShopWindow.add(upgradeBtn);
            coinsShopWindow.add(luckyupgradeBtn);
            coinsShopWindow.add(autoClickerBuyBtn);
            coinsShopWindow.add(autoClickerSpeedUpgradeBtn);
            coinsShopWindow.add(autoClickerPowerUpgradeBtn);
            coinsShopWindow.setVisible(true);
        });
        
        openGemsShopBtn.addActionListener(e -> {
            JDialog gemsShopWindow = new JDialog(frame, "Upgrade Shop", true); 
            gemsShopWindow.setLayout(new GridLayout(0, 1, 10, 10));
            gemsShopWindow.setSize(300, 500);
            gemsShopWindow.setLocationRelativeTo(frame);
            gemsShopWindow.add(gemsLuckyUpgradeBtn);
            gemsShopWindow.add(gemsAutoClickerBuyBtn);
            gemsShopWindow.add(gemsAutoClickerPowerUpgradeBtn);
            gemsShopWindow.add(gemsAutoClickerSpeedUpgradeBtn);
            gemsShopWindow.add(gem1TradeInBtn);
            gemsShopWindow.add(gem10TradeInBtn);
            gemsShopWindow.add(gem100TradeInBtn);
            gemsShopWindow.add(gem1000TradeInBtn);
            gemsShopWindow.setVisible(true);
        });
        
        final JDialog endingWindow = new JDialog(frame, "Ending", true);

        playAgainBtn.addActionListener(t -> {
        endingWindow.dispose();
        });

        endingBtn.addActionListener(e -> {
            if (coins >= 150000 && gems >= 10000 && Rebirths >= 2) {
            coins -= 150000;
            coinLabel.setText("   Coins: " + coins);
            Rebirths -= 2;
            RebirthsLabel.setText("   Rebirths: " + Rebirths);
            gems -= 10000;
            gemsLabel.setText("   Gems: " + gems);
            endingWindow.dispose(); 
            endingWindow.setUndecorated(true);
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            endingWindow.setSize(screenSize);
            endingWindow.setLayout(new FlowLayout());
            endingWindow.add(playAgainBtn);
            endingWindow.add(exitBtn);
            endingWindow.revalidate();
            endingWindow.repaint();
            endingWindow.setVisible(true);
            }
            else if(coins < 150000)
            {
                endingBtn.setText("Not enough coins");
                Timer g = new Timer(1000, f -> endingBtn.setText("To Win You Need: 150,000 Coins, 10,000 Gems, and 2 Rebirths"));
                g.setRepeats(false);
                g.start();
            }
            else if(gems < 10000)
            {
                endingBtn.setText("Not enough gems");
                Timer g = new Timer(1000, f -> endingBtn.setText("To Win You Need: 150,000 Coins, 10,000 Gems, and 2 Rebirths"));
                g.setRepeats(false);
                g.start();
            }
            else if(Rebirths < 2)
            {
                endingBtn.setText("Not enough Rebirths");
                Timer g = new Timer(1000, f -> endingBtn.setText("To Win You Need: 150,000 Coins, 10,000 Gems, and 2 Rebirths"));
                g.setRepeats(false);
                g.start();
            }
        });
        
        playAgainBtn.addActionListener(t -> {
            endingWindow.dispose();
        });
        
        exitBtn.addActionListener(t -> {
        System.exit(0);
        });

        
        engine.start();
        engine2.start();

        frame.add(coinLabel);
        frame.add(gemsLabel);
        frame.add(RebirthsLabel);
        frame.add(workBtn);
        frame.add(openCoinsShopBtn);
        frame.add(openGemsShopBtn);
        frame.add(endingBtn);
        
        frame.setSize(400,400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    
};
}

