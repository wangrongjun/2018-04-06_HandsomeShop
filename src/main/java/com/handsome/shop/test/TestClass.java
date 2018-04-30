package com.handsome.shop.test;

import com.wangrj.java_lib.java_util.MathUtil;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

/**
 * by wangrongjun on 2017/11/3.
 */
public class TestClass {

    public static void main(String[] args) throws AWTException {
        Robot robot = new Robot();

// 模拟一次按键，一定要有随机的时间间隔，否则会被游戏商检测到外挂
        robot.delay(MathUtil.random(500, 1000));
        robot.keyPress(KeyEvent.VK_A);
        robot.delay(MathUtil.random(200, 400));
        robot.keyRelease(KeyEvent.VK_A);

// 模拟鼠标移动和点击，参数就是屏幕像素，可以到控制面板的屏幕属性中查
        robot.mouseMove(1366 / 6, 768 / 3);
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseMove(1366 / 5, 768 / 4);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

// 获取屏幕某一点的颜色（判断血条状态，是否需要补血。或者吃鸡蹲点时监测红点是否变化，一变化马上开枪）
        Color pixelColor = robot.getPixelColor(2, 50);
        System.out.println(pixelColor.getRed());
        System.out.println(pixelColor.getBlue());
        System.out.println(pixelColor.getGreen());
    }

}
