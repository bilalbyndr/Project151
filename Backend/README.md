In Spring Security 5.7.0-M2 the WebSecurityConfigurerAdapter became deprecated, as they now encourage users to move towards a component-based security configuration. Check https://spring.io/blog/2022/02/21/spring-security-without-the-websecurityconfigureradapter

A1: 
• Ein Salt wird verwendet, um jeden Hash einzigartig zu machen und ist in der Datenbank neben dem Hash gespeichert.
• Ein Pepper ist ein zusätzliches Geheimnis, das allen Passwörtern hinzugefügt wird, aber ausserhalb der Datenbank gespeichert wird, um die Sicherheit zu erhöhen.
• Salt (Salz): Ein einzigartiger, zufälliger Wert, der vor dem Hashing zu jedem Passwort hinzugefügt wird. Er schützt gegen Rainbow-Table-Angriffe, indem er jeden Hash einzigartig macht.
• Pepper (Pfeffer): Ein geheimer Wert, der zu allen Passwörtern vor dem Hashing hinzugefügt wird und außerhalb der Datenbank gespeichert wird. Er bietet eine zusätzliche Sicherheitsebene gegen Datenbankangriffe.
Während ein Salt mit dem Hash gespeichert und für jeden Benutzer unterschiedlich ist, ist ein Pepper ein einzelner, geheimer Wert, der für alle Hashes verwendet und nicht mit ihnen gespeichert wird.



React:
A2:

- XSS-Angriffe (Cross-Site Scripting):
Problem:
Wenn eine Website von XSS betroffen ist, kann ein Angreifer bösartigen Code in die Seite einschleusen und dadurch Zugriff auf den LocalStorage erhalten.
Konsequenz:
Durch den gestohlenen Zugriff auf den LocalStorage könnte der Angreifer das JWT extrahieren und sich als der betroffene Benutzer ausgeben. Das ermöglicht ihm, auf geschützte Ressourcen zuzugreifen und Aktionen im Namen des Benutzers durchzuführen.

- Kein automatischer CSRF-Schutz (Cross-Site Request Forgery):
Problem:
JWTs im LocalStorage werden automatisch zu jeder Anfrage an denselben Server hinzugefügt, ohne einen automatischen Schutz vor CSRF.

Konsequenz:
Ohne zusätzliche Sicherheitsmaßnahmen besteht die Gefahr von CSRF-Angriffen, bei denen ein Angreifer einen authentifizierten Benutzer dazu bringen könnte, ungewollte Aktionen auf der Website durchzuführen.

- Keine automatische Ablaufüberwachung:
Problem:
Der LocalStorage bietet keine integrierte Funktion zur automatischen Überwachung des Ablaufs (Expiration) eines JWTs.

Konsequenz:
Ein abgelaufenes JWT könnte im LocalStorage verbleiben, was zu Sicherheitsproblemen führen würde. Manuelle Maßnahmen wären erforderlich, um abgelaufene JWTs zu erkennen und zu handhaben.



Bessere Alternative: Verwendung von HttpOnly-Cookies für JWTs:

Sicherheit gegen XSS:
Cookies mit dem HttpOnly-Flag sind vor JavaScript-basierten Zugriffen geschützt, was die Ausnutzung von XSS-Angriffen erheblich erschwert.

Automatischer CSRF-Schutz:
Cookies werden standardmässig nicht in Anfragen von Drittanbieter-Scripts eingefügt, was einen automatischen Schutz vor CSRF-Angriffen bietet.

Integrierte Ablaufüberwachung:
Cookies können mit einem Ablaufdatum versehen werden, und der Browser kümmert sich automatisch um die Überwachung des Ablaufs.

Warum das besser ist:
Die Verwendung von HttpOnly-Cookies bietet daher eine sicherere Methode zur Speicherung von Authentifizierungstoken im Vergleich zum LocalStorage, da sie diese potenziellen Sicherheitsrisiken mindert.