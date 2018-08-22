import random
import sys
import os

class Animal:
    def __init__(self, name, height, weight, sound):
        self.name = name
        self.height = height
        self.weight = weight
        self.sound = sound

    def __str__(self):
        return "{} is {} cm tall and {} kilograms and says {}".format(self.name, self.height, self.weight, self.sound)

cat = Animal("Whiskers", 33, 10, "Meow")

print(cat)

class Dog(Animal):

    def __init__(self, name, height, weight, sound, owner):
        self.owner = owner
        super(Dog, self).__init__(name, height, weight, sound)

    def __str__(self):
        return super(Dog, self).__str__() + ". His owner is {}".format(self.owner)

    def multiple_sounds(self, how_many=1):
        return (self.sound + " ") * how_many

dog = Dog("Spot", 53, 27, "Ruff", "Derek")

print(dog)

print(dog.multiple_sounds())
print(dog.multiple_sounds(4))
