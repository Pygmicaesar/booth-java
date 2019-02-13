# BOOTH OUTPUT

> Data available in RWLAN_PUBLIC `10.0.4.246` port `5555`

## CSV output with following parameters from Arduino.

```
#R1,0,0,-60,116,0,0,0,0,0,0,0,0,100,0,0,-1,132,0,26,0
#S1,24.39,35.36,100766.84,603,0,363,48,11,-1
#R1,0,0,-60,116,0,0,0,0,0,0,0,0,100,0,0,-1,132,0,26,0
#R1,0,0,-60,116,0,0,0,0,0,0,0,0,100,0,0,-1,132,0,26,0
#R1,0,0,-60,116,0,0,0,0,0,0,0,0,100,0,0,-1,132,0,26,0
#R1,0,0,-60,116,0,0,0,0,0,0,0,0,100,0,0,-1,132,0,26,0
#R1,0,0,-60,116,0,0,0,0,0,0,0,0,100,0,0,-1,132,0,26,0
#R1,0,0,-60,116,0,0,0,0,0,0,0,0,100,0,0,-1,132,0,26,0
#R1,0,0,-60,116,0,0,0,0,0,0,0,0,100,0,0,-1,132,0,26,0
#C1,5218,-311,-17,-15072,-10769,711,357,609,557,-7994,-6078,25
#R1,0,0,-60,116,0,0,0,0,0,0,0,0,100,0,0,-1,132,0,26,0
#R1,0,0,-60,116,0,0,0,0,0,0,0,0,100,0,0,-1,132,0,26,0
```

### 1) Capacitive data (once every two seconds), calibrated to zero at reset
Example output `#C1,5218,-311,-17,-15072,-10769,711,357,609,557,-7994,-6078,25`

> `#C1,<param1>,<param2>,...,<paramN>`

    Capacitive channel 1 reading (int16), RX0, Floor
    Capacitive channel 2 reading (int16), RX0, Chair
    Capacitive channel 3 reading (int16), RX0, Desk
    Capacitive channel 4 reading (int16), RX0, Door handle
    Capacitive channel 5 reading (int16), RX0, Door angle
    Capacitive channel 6 reading (int16), RX0, Feedback strip
    Capacitive channel 7 reading (int16), RX1, Floor
    Capacitive channel 8 reading (int16), RX1, Chair
    Capacitive channel 9 reading (int16), RX1, Desk
    Capacitive channel 10 reading (int16), RX1, Door handle
    Capacitive channel 11 reading (int16), RX1, Door angle
    Capacitive channel 12 reading (int16), RX1, Feedback strip

### 2) Realtime parsed data (several times a second)
Example output `#R1,0,0,-60,116,0,0,0,0,0,0,0,0,100,0,0,-1,132,0,26,0`

> `#R1,<param1>,<param2>,...,<paramN>`

    Presence inside (bool 1/0)
    Presence outside (bool 1/0)
    Position X (int16), origin at booth center
    Position Y (int16), origin at booth center
    Standing (bool 1/0)
    Height measurement standing (int8, arbitrary unit)
    Sitting (bool 1/0)
    Height measurement sitting (int8, arbitrary unit)
    RESERVED
    Touching table (bool 1/0)
    Object on table (bool 1/0)
    Touching door handle (bool 1/0)
    Distance from door handle (int8, cm)
    Gesturing (bool 1/0)
    RESERVED
    Continuous feedback score (int8_t, valid value 0-100, -1 = not available)
    LED hue (uint16_t, 0-360 in degrees)
    RESERVED
    Door angle (int8, degrees, 0=closed)
    RESERVED

### 3) Slow data, (once every two seconds)
Example output `#S1,24.38,35.36,100765.94,603,0,363,48,11,-1`

> `#S1,<param1>,<param2>,...,<paramN>`

    Temperature (int8, C)
    Relative humidity (uint8, %)
    Air pressure (uint16, mBar)
    CO2 level (uint16, ppm)
    TVOC level (uint16, ppm)
    Visible spectrum light level (uint16)
    IR spectrum light level (uint16)
    Illuminance (uint16, lux)
    Feedback score final (int8, valid value of 0-100 is sent only once, -1 = not available)
