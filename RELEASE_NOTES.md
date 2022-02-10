<h1>RELEASE NOTES</h1>
<h2>goranc java refactoring</h2>
<h2>2022-02-10</h2>

<h3>Development environment</h3>
<li>Java 17</li>
<li>Gradle 7.4</li>
<li>Junit 5</li>

To run tests:
<p><code>./gradlew clean build test</code></p>

To run application:
<p><code>./gradlew clean build run</code></p>

To compile without gradle:
<p>
<code>javac app/src/main/java/refactoring/java/*.java app/src/main/java/refactoring/java/config/*.java app/src/main/java/refactoring/java/model/*.java app/src/main/java/refactoring/java/service/*.java app/src/main/java/refactoring/java/statement/*.java</code>
</p>

To run without gradle:
<p>
<code>java -cp app/src/main/java refactoring.java.Main</code>
</p>

<h3>Refacorings performed</h2>
I started to create a TODO.md file where I wrote down the refactorings I intended to do. The TODO list grew over time.

Unfortunatly I am a little time constrained so there are a few improvements that I intended to do that I didn't have time to perform.
As an example I intended to have an interface to the statement report generator class so that you easily could provide another implementation of the report (e g in another format).

<li>Made use of gradle and Junit5 (Jupiter)</li>
<li>Created a package for model classes</li>
<li>Created unit tests</li>
<li>Created an enum for the movie categories</li>
<li>Create a class for the MovieRepository</li>
<li>Separated pricing and loyalty point calculation from statement creation</li>
<li>>Checked for null pointers</li>
<li>Made use of StringBuilder instead of String concatenation</li>
<li>Improved variable and class names</li>
<li>Separated statement data compilation a from statement report generation</li>
<li>Introduced configuration interface and implementation that provides acess to movie repository, price and loyalty point calculators</li>
<li>Formatted all source files to use same indentation standard</li>
<li>Documentation: Commented major classes, wrote RELEASE NOTES</li>
