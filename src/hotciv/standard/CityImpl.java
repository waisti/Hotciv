package hotciv.standard;

import hotciv.framework.*;

public class CityImpl implements City {
  private Player owner;
  private int storage;
  private String production;
  //public Player owner = Player.RED;

  public CityImpl(Player owner){
    this.owner = owner;
    storage = 0;
  }

  @Override
  public Player getOwner() {
    return owner;
  }

  @Override
  public int getSize() {
    return 1;
  }

  @Override
  public String getProduction() {
    return production;
  }

  @Override
  public String getWorkforceFocus() {
    return null;
  }

  public int getStorageSize() {
    return storage;
  }
public void setStorage(int production){
  storage += production;
}
public void setProduction(String production){
  this.production = production;

}
public void setOwner(Player player){
  owner = player;
}

}
