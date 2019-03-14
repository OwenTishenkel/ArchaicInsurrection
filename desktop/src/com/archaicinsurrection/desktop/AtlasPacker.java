package com.archaicinsurrection.desktop;

import com.badlogic.gdx.tools.texturepacker.TexturePacker;

public class AtlasPacker {

    public static final String INPUT_PATH ="Sprites/Input/";
    public static final String OUTPUT_PATH="Sprites/Output/";

    public static void main(String[] args) {
        TexturePacker.Settings settings = new TexturePacker.Settings();
        settings.maxHeight= 2048;//phone game lol
        settings.maxWidth= 2048;
        TexturePacker.process(settings,INPUT_PATH,OUTPUT_PATH,"ArchaicInsurrectionAtlas");

    }



}
