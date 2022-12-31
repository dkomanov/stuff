package com.komanov.compression.jmh;

import java.nio.file.Path;
import java.nio.file.Paths;

public enum InputData {
    total_298_with_5_items("/home/dkomanov/1/bins/total_298_with_5_items.bin"),
    total_319_with_5_items("/home/dkomanov/1/bins/total_319_with_5_items.bin"),
    total_320_with_5_items("/home/dkomanov/1/bins/total_320_with_5_items.bin"),
    total_326_with_5_items("/home/dkomanov/1/bins/total_326_with_5_items.bin"),
    total_366_with_5_items("/home/dkomanov/1/bins/total_366_with_5_items.bin"),
    total_372_with_5_items("/home/dkomanov/1/bins/total_372_with_5_items.bin"),
    total_648_with_5_items("/home/dkomanov/1/bins/total_648_with_5_items.bin"),
    total_420_with_6_items("/home/dkomanov/1/bins/total_420_with_6_items.bin"),
    total_459_with_7_items("/home/dkomanov/1/bins/total_459_with_7_items.bin"),
    total_531_with_8_items("/home/dkomanov/1/bins/total_531_with_8_items.bin"),
    total_686_with_7_items("/home/dkomanov/1/bins/total_686_with_7_items.bin"),
    total_562_with_9_items("/home/dkomanov/1/bins/total_562_with_9_items.bin"),
    total_479_with_10_items("/home/dkomanov/1/bins/total_479_with_10_items.bin"),
    total_538_with_8_items("/home/dkomanov/1/bins/total_538_with_8_items.bin"),
    total_665_with_10_items("/home/dkomanov/1/bins/total_665_with_10_items.bin"),
    total_34011_with_526_items("/home/dkomanov/1/bins/total_34011_with_526_items.bin"),
    total_35578_with_536_items("/home/dkomanov/1/bins/total_35578_with_536_items.bin"),
    total_51771_with_612_items("/home/dkomanov/1/bins/total_51771_with_612_items.bin"),
    total_52928_with_610_items("/home/dkomanov/1/bins/total_52928_with_610_items.bin"),
    total_59448_with_586_items("/home/dkomanov/1/bins/total_59448_with_586_items.bin"),
    total_59617_with_636_items("/home/dkomanov/1/bins/total_59617_with_636_items.bin"),
    total_68118_with_646_items("/home/dkomanov/1/bins/total_68118_with_646_items.bin"),
    total_67071_with_644_items("/home/dkomanov/1/bins/total_67071_with_644_items.bin"),
    total_42223_with_674_items("/home/dkomanov/1/bins/total_42223_with_674_items.bin"),
    total_62830_with_824_items("/home/dkomanov/1/bins/total_62830_with_824_items.bin"),
    total_67872_with_843_items("/home/dkomanov/1/bins/total_67872_with_843_items.bin"),
    total_79107_with_645_items("/home/dkomanov/1/bins/total_79107_with_645_items.bin"),
    total_68230_with_549_items("/home/dkomanov/1/bins/total_68230_with_549_items.bin"),
    total_81207_with_912_items("/home/dkomanov/1/bins/total_81207_with_912_items.bin"),
    total_88114_with_888_items("/home/dkomanov/1/bins/total_88114_with_888_items.bin"),
    total_94417_with_951_items("/home/dkomanov/1/bins/total_94417_with_951_items.bin"),
    total_607930_with_11954_items("/home/dkomanov/1/bins/total_607930_with_11954_items.bin"),
    total_773419_with_11788_items("/home/dkomanov/1/bins/total_773419_with_11788_items.bin"),
    total_989390_with_12348_items("/home/dkomanov/1/bins/total_989390_with_12348_items.bin"),
    total_781196_with_13188_items("/home/dkomanov/1/bins/total_781196_with_13188_items.bin"),
    total_668462_with_12792_items("/home/dkomanov/1/bins/total_668462_with_12792_items.bin"),
    total_751048_with_12663_items("/home/dkomanov/1/bins/total_751048_with_12663_items.bin"),
    total_1075724_with_11955_items("/home/dkomanov/1/bins/total_1075724_with_11955_items.bin"),
    total_791173_with_12544_items("/home/dkomanov/1/bins/total_791173_with_12544_items.bin"),
    total_1293080_with_11548_items("/home/dkomanov/1/bins/total_1293080_with_11548_items.bin"),
    total_904172_with_14336_items("/home/dkomanov/1/bins/total_904172_with_14336_items.bin"),
    total_1356567_with_14214_items("/home/dkomanov/1/bins/total_1356567_with_14214_items.bin"),
    total_1599048_with_16068_items("/home/dkomanov/1/bins/total_1599048_with_16068_items.bin"),
    total_866049_with_18957_items("/home/dkomanov/1/bins/total_866049_with_18957_items.bin"),
    total_1448911_with_26013_items("/home/dkomanov/1/bins/total_1448911_with_26013_items.bin"),
    total_4072805_with_34010_items("/home/dkomanov/1/bins/total_4072805_with_34010_items.bin"),
    total_4287156_with_35800_items("/home/dkomanov/1/bins/total_4287156_with_35800_items.bin"),
    ;

    public final Path path;

    InputData(String path) {
        this.path = Paths.get(path);
    }
}
