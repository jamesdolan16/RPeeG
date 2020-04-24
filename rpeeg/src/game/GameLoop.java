package game;

import java.util.TimerTask;

public class GameLoop extends TimerTask {

    ScreenDrawer _out;
    World _world;
    Player _player;
    ViewKeyListener _viewKeys;
    char previousCommand;
    Boolean _runFlag;

    public GameLoop(ScreenDrawer out, World world, Player player, ViewKeyListener viewKeys, Boolean run){
        _out = out;
        _world = world;
        _player = player;
        _viewKeys = viewKeys;
        _runFlag = run;
    }

    public void run(){
        Update();
        _out.drawScreen(_player, _world);

        char currentCommand = _viewKeys.CurrentKey();
        //Get if key press
        switch(currentCommand) {						//Just for now
            case 'w':
                if(!WallCollision(0,-1)) {
                    _player.moveNorth();
                }
                break;
            case 's':
                if(!WallCollision(0,1)) {
                    _player.moveSouth();
                }
                break;
            case 'a':
                if(!WallCollision(-1,0)) {
                    _player.moveWest();
                }
                break;
            case 'd':
                if(!WallCollision(1,0)) {
                    _player.moveEast();
                }
                break;
            case 'p':
                try {
                    _player.pickUp(_world.getAreaByID(_player.currentArea).getItemByPos(_player.pos));
                    _world.getAreaByID(_player.currentArea).removeItem(_player.pos);
                }catch(NullPointerException e) {
                    _out.print("Nothing to pick up!\n");
                }
                break;
            case 'i':	//Inventory
                _out.drawInventory(_player);
                break;
            case 'q':
                _runFlag = false;
                this.cancel();
                break;

        }
        _viewKeys.SetCurrentKey('0');

    }

    public void Update() {
        //Door stuff
        DoorCollision(_player.pos);

    }

    private void DoorCollision(Vec2 pos) {
        Area currentArea = _world.getAreaByID(_player.currentArea);
        Door currentDoor = currentArea.getDoorByPos(pos);
        if(currentDoor != null) {	//Go through door
            _player.currentArea = currentDoor.DestAreaID;
            _player.pos.x = currentDoor.DestCoord.x;
            _player.pos.y = currentDoor.DestCoord.y;
        }
    }

    private boolean WallCollision(int dx, int dy) {
        Vec2 dpos = new Vec2();
        dpos.x = _player.pos.x+dx; dpos.y = _player.pos.y+dy;
        char edge = _world.getAreaByID(_player.currentArea).Map[dpos.y][dpos.x];
        if(edge == '#') {
            return true;
        }
        else {
            return false;
        }
    }
}
