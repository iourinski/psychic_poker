# psychic_poker
This is the source code for the script available as a jar file (see Google disk link).
In case if you have sbt on your system the script can be run as 
from the project's root directory run 

`sbt assembly`

this creates fat jar that can be run using java, for example:

`java -cp target/scala-2.12/psychic_poker-assembly-0.1.jar app.Runner`

which runs the script interactively (you have to enter data), until you hit enter twice (empty line).

`java -cp target/scala-2.12/psychic_poker-assembly-0.1.jar app.Runner < src/main/resources/hands.txt`

the above reads data from a file and processes the whole batch.

# why it is written this way and not some other
As this is supposed to be a beginning of possibly extensible project, I tried to maximally separate game logic, input processing and the pocker hands themselves: assuming that the set of poker hands can change, the dealing rules can change (different number of cards in dealt hand and lookup etc), perhaps the deck can change too (I haven't heard about playing poker with tarot cards, but it does not seem impossible).
