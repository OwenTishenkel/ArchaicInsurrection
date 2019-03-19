package Managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.maps.tiled.BaseTmxMapLoader;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

public class MyAssetManager {

    private static final String TAG = MyAssetManager.class.getSimpleName();
    private AssetManager assetManager;

    public MyAssetManager(AssetManager assetManager) {
        this.assetManager = assetManager;
    }

    public void unloadAsset(String assetFilePath) {
        if(assetManager.isLoaded(assetFilePath)) {
            assetManager.unload(assetFilePath);
        }
        else {
            Gdx.app.log(TAG,"Asset is not in memory: "+assetFilePath);
        }
    }
    public float loadCompleted(){
        return assetManager.getProgress();
    }
    public int numberAssetsQueued(){
        return assetManager.getQueuedAssets();
    }
    public boolean updateAssetLoading(){
        return assetManager.update();
    }
    public boolean isAssetLoaded(String filename){
        return assetManager.isLoaded(filename);
    }
    public void loadMapAsset(String filepath){
        if(filepath==null || filepath.isEmpty()) {
            Gdx.app.log(TAG,"Asset not found: "+filepath);
            return;
        }
        if(assetManager.getFileHandleResolver().resolve(filepath).exists()){
            assetManager.setLoader(TiledMap.class, new TmxMapLoader(assetManager.getFileHandleResolver()));
            assetManager.load(filepath, TiledMap.class);
            //we are going to block to finish loading all at once
          //  assetManager.finishLoadingAsset(filepath);
            Gdx.app.log(TAG,"Map Loaded: "+filepath);
        }
        else {
            Gdx.app.log(TAG,"Map doesn't exist: "+filepath);
        }

    }

    public TiledMap getMapAsset(String filepath) {
        TiledMap map;

        if(assetManager.isLoaded(filepath)) {
            map=assetManager.get(filepath,TiledMap.class);
            Gdx.app.log(TAG,"Map loaded: "+filepath+" "+map);

            return map;
        }
        else{
            Gdx.app.log(TAG,"Map is not loaded: "+filepath);
             map= null;
            return map;

        }
    }
}
