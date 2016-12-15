package hotciv.standard;

/**
 * Created by troels on 25/11/2016.
 */
public interface GameFactory {
    public WinnerStrategy createWinnerStrategy();
    public WorldStrategy createWorldStrategy();
    public UnitStrategy createUnitStrategy();
    public WorldLayoutStrategy createWorldLayoutStrategy();
    public BattleStrategy createBattleStrategy();


}
