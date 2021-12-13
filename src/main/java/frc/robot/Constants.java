// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    
    // Drivetrain

    public final int DT_LEFT_MASTER_ID = 1;
    public final int DT_LEFT_SLAVE_ID = 2;
    public final int DT_RIGHT_MASTER_ID = 5;
    public final int DT_RIGHT_SLAVE_ID = 6;

    public final int DT_SHIFTER_F = 0;
    public final int DT_SHIFTER_B = 7;
    
    public final int DT_RIGHT_ENC_A = 4;
    public final int DT_RIGHT_ENC_B = 5;
    public final int DT_LEFT_ENC_A = 6;
    public final int DT_LEFT_ENC_B = 7;

    // Flywheel

    public final int FW_MASTER_FALCON_ID = 10;
    public final int FW_SLAVE_FALCON_ID = 11;
    public final int FW_ENC_A = 0;
    public final int FW_ENC_B = 1;

    // Climber

    public final int CB_TRANSLATOR_TALON_ID = 32;
    public final int CB_RAISE_ONE_ID = 30;
    public final int CB_RAISE_TWO_ID = 31;

    public final int CB_BRAKE_F = 6;
    public final int CB_BRAKE_B = 1;

    // Tower

    public final int TW_FEEDER_ID = 12;

    // Colour Wheel

    public final int CW_SPINNER_ID = 45;

    //Hood

    public final int HD_ACTUATOR_ID = 9;

    //Intake

    public final int IT_DROP_DOWN_ID = 41;
    public final int IT_DRIVE_ID = 13;
}
