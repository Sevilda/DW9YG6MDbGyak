package iit;

import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Hello {
	
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		try (Connection  con = DriverManager.getConnection("jdbc:neo4j:neo4j://localhost:7687", "neo4j", "admin");
				Statement stmt = con.createStatement()) {
			ResultSet rs = stmt.executeQuery("MATCH (n:User) with n.username as name RETURN name");
			while (rs.next()) {
				System.out.println(rs.getString("name"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//get user
		String username = getUsername(sc);
		
		
		//get tweet (first 5 words is short
		//get current date
		
		String tweet = readTweet(sc);
		
		//tweet
		
		final DateTimeFormatter dtf = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
		
		try (Connection  con = DriverManager.getConnection("jdbc:neo4j:neo4j://localhost:7687", "neo4j", "admin");
				PreparedStatement pst = con.prepareStatement("create (t:Tweet{short:$1, created:datetime($2), text:$3})")) {
			pst.setString(1, tweet.substring(0, 5));
			pst.setString(2, LocalDateTime.now().format(dtf));
			System.out.println(tweet);
			pst.setString(3, tweet);
			System.out.println("Executed statement: " + pst.execute());
			con.close();
				}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		//kapcsolat/:authored
		
		try (Connection  con = DriverManager.getConnection("jdbc:neo4j:neo4j://localhost:7687", "neo4j", "admin");
				PreparedStatement pst = con.prepareStatement("match (u:User), (t:Tweet) where  u.username =$1 and t.short=$2 create (t)<-[:authored]-(u)")) {
				pst.setString(1, username);
				pst.setString(2, tweet.substring(0, 5));
				System.out.println("Executed statement: " + pst.execute());
				con.close();
				}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		}
	
	static String getUsername(Scanner sc) {
		System.out.println("Please input your name: \n");
		return sc.nextLine();
		
	}
	
	static String readTweet(Scanner sc) {
		System.out.println("Please input your tweeet: \n");
		return sc.nextLine();
		}
}


/*
 * 
A program el??sz??r ki??rja az adatb??zisban tal??lhat?? felhaszn??l??k neveit.
Bek??ri, hogy ki vagy te.
Bek??ri a tweetet, amit k??sz??teni akarsz.
A be??rt sz??veg lesz a Tweet text mez??je, a Tweet els?? 5 szava short mez??je, a d??tum pedig a mostani pillanat.
Elk??sz??ti a tweetet ??s a kapcsolatot a tweet ??s a User k??z??tt, ahol a kapcsolat labelje :authored.
Ki??rja a sikert, ??s exit??l.

 * 
 * */

