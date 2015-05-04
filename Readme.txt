This is the code for the classic game of Tetris.

This is not original. First, the game was programmed by Alexey Pajitnov, and released in 1984.

My code is translated from a version provided to me in a class by Dan Grossman (http://courses.cs.washington.edu/courses/cse341/13sp/)

As a homework assignment in my Programming Languages class (taken through Coursera) we modified a Tetris program, 
adding new features such as extra pieces and various cheats. I thought a good way to learn Java would be to 
translate the program (written in Ruby) to Java. This turned out to be much more difficult than I expected - I almost
wish I had started from scratch. I had a lot of difficutlies with having implicit assumptions about how the game would
work, especially with the GUI, that were incorrect. Most of my trouble came from various libraries which implemented
things (Timers, Panels, Buttons...) in different ways. I often tried to tweak the original Ruby representations a
little bit at a time, but what I really needed to do was think about how to put the pieces together in a different way.

An example of this is that the Ruby Gui had code to add various graphic objects to the canvas, where they would
automatically show up. In Ruby I could add them, but had to ovveride a paint method on the canvas to get the data
from the objects to paint them. It took me a while before I realized that was what I needed to do, as opposed to
finding a way to add paint-methods to the objects. (Only now do I realize that the Ruby library may have worked 
similarly to Java, with a single paint method that is not visible)

The Oracle tutorials were helpful, but confusing. I think I would save time reading a book on whatever library
I'm trying to use before I start. Otherwise I get lost in the details and it is a while before I find the page 
explaining the larger picture.
