# Student Management System
---
## Completion
Not yet completed.
## Motivation
I want to develop my sense of software engineering in Java. In order of that reason, I look around some management system application and found some tutorial. In that tutorial, some requirements are given and I started from there.
## Summary
Main window is in StudentForm.java, it contains panel to add students data and some buttons to do its function. Some features that need server are from Search button (solr), Store (oracle database), and Rettrieve (Oracle database).
### Button overview
* Can be tried
Add (which also can be done by enter), Display, Delete, Save, Edit, Load, Score, Serve(localhost:8001)
* Must be modified to be tried
Store, Rerieve, Search
* Nothing
Library, Canteen, Course, Study, Summary, Web, Log, Trend, Connect
### IDE used
Eclipse IDE for Java Developers
## Technology used
* Java : the programming language
** Java 11
** Internal Library
** External Library : solr, jdbc, log4j (not considering cve unsecurity)
* Swing : the GUI library used
** JFrame
** JPanel
** JScrollPane
** JLabel
** JTextField
** JButton
** JFileChooser
** JTable
** HttpServer
** SpringLayout
** GridLayout
** GridBagLayout
** ActionListener
** DocumentListener
* JDBC
* SQL
* Solr
Minimal implementation: Not yet related , using dummy.
## Structure
* There is Student and Score entity
* Students saved in ArrayList
* Score is related to Student
* Dominated by String until now
## Screenshots
