# MotorLab

## Guide:
[Motor Lab Guide Link](https://docs.google.com/document/d/1jdfxj9Nu2IgoPEUOYdsv6O2Ke6_kR-LyUUz0fArGj8s/edit?usp=sharing)

[DriveFunctions.java](src/main/java/com/stuypulse/robot/commands/DriveFunctions.java)

## Challenges:

### 1: Drive forwards
No limits on how long or how far, just get your romi to drive straight!

Use `void driveForwards(Motor left, Motor right) {}`.

### 2: Drive backwards
Just like the last command, but backwards.

Use `void driveBackwards(Motor left, Motor right) {}`.

### 3: Turn in place
Make your romi turn clockwise in place. You'll need to think about this one!

Use `void turnRight(Motor left, Motor right) {}`.

### 4: Turn left
Make your romi turn counter-clockwise in place.

Use `void turnLeft(Motor left, Motor right) {}`.

### 5: Arcing left and right
Instead of turning in place or moving straight, try to get your romi to move forward while turning.

Use `void arcRight(Motor left, Motor right) {}` and `void arcLeft(Motor left, Motor right) {}`.

### 6: Stopping
Until this point, all of our commands have run without end, probably with some bad consequences. Let's make our romi stop after travelling 2 feet forwards.

Use `void stopDistance(Motor left, Motor right) {}`.

### 7: Bang bang
Although our romi does stop, you might have noticed that it overshoots our target a little bit. Lets say that we really need our romi to be on target. How about we make the romi move back if it overshot and forward if it undershoots?

Use `void bangBang(Motor left, Motor right) {}`.

### 8: Better control
We saw in the last command that, although Bang-bang does bring the romi back to the target, it also has a lot of oscillation. How can we try and fix this? Can we make the romi's speed depend on how far it is from the target?

Use `void betterControl(Motor left, Motor right) {}`.
