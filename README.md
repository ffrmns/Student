![inputValidationStudent](https://user-images.githubusercontent.com/42773358/146238900-bd6a8487-3260-4a4c-8944-320ffed6ef6a.png)
# Student Management System
---
## Completion
Not yet completed.
## Motivation
I want to develop my sense of software engineering in Java. In order of that reason, I look around some management system application and found some tutorial. In that tutorial, some requirements are given and I started from there.
## Summary
Main window is in StudentForm.java, it contains panel to add students data and some buttons to do its function. Some features that need server are from Search button (solr), Store (oracle database), and Rettrieve (Oracle database). Currently, the Score also using the main for experimenting, it uses separated data from main window.
### Button overview
* Can be tried
  * Add (which also can be done by enter), Display, Delete, Save, Edit, Load, Score(run separatedly from ScoreFrame), Serve(localhost:8001)
  * Must be modified to be tried
    * Store, Rerieve, Search
  * Nothing
    * Library, Canteen, Course, Study, Summary, Web, Log, Trend, Connect
### IDE used
Eclipse IDE for Java Developers
## Technology used
* Java : the programming language
  * Java 11
    * Internal Library
    * External Library : solr, jdbc, log4j (not considering cve unsecurity)
* Swing : the GUI library used
  * JFrame
  * JPanel
  * JScrollPane
  * JLabel
  * JTextField
  * JButton
  * JFileChooser
  * JTable
  * HttpServer
  * SpringLayout
  * GridLayout
  * GridBagLayout
  * ActionListener
  * DocumentListener
* JDBC
* SQL
* Solr
    * Minimal implementation: Not yet related , using dummy.
## Structure
* There is Student and Score entity
* Students saved in ArrayList
* Score is related to Student
* Dominated by String until now
## Screenshots
Run in Ubuntu with XMonad WM
![displayStudent](https://user-images.githubusercontent.com/42773358/146239008-d218f57f-1807-4d62-b27e-49d0e0cc4825.png)
![editStudent](https://user-images.githubusercontent.com/42773358/146239140-9a0fa963-5d5b-46fa-8200-9ef308c17687.png)
![saveStudent](https://user-images.githubusercontent.com/42773358/146239183-7640bece-a410-4410-82d7-4192d7450bd7.png)
![manageScore](https://user-images.githubusercontent.com/42773358/146239202-1543b2c1-bdf3-4bfa-b712-1b71f6d9bfdc.png)
## Finally
Thank you for reading! Feel free to do anything!
