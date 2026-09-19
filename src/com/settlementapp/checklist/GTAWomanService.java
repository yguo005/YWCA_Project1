package com.settlementapp.checklist;

import java.util.Random;
import java.util.Scanner;

public class GTAWomanService {
    static class Links {
        public String website;
        String name;
        String website1;
        String website2;
        String website3;
        String website4;
        String website5;

        Links(String name, String website1, String website2, String website3, String website4, String website5) {
            this.name = name;
            this.website1 = website1;
            this.website2 = website2;
            this.website3 = website3;
            this.website4 = website4;
            this.website5 = website5;
        }

    }

    static void main(String[] args) {
        Links[] links = new Links[10];

        //GTAWomanService.Links[] links = new GTAWomanService.Links[10];
        links[0] = new Links("GTA Transit Info Guide.", "https://transittoronto.ca/info.shtml",
                "", "", "", "");
        links[1] = new Links("GTA Housing Service", "https://www.shhc.ca/newcomers",
                "", "", "", "");
        links[2] = new Links("Canada History Book", "https://www.canada.ca/content/dam/ircc/migration/ircc/english/pdf/pub/discover.pdf", "https://www.britannica.com/place/Ontario-province"
                , "https://en.wikipedia.org/wiki/History_of_Ontario", "", "");
        links[3] = new Links("Food Banks", "https://gtafoodbanks.ca"
                , "https://www.dailybread.ca", "https://211central.ca/results/?searchLocation=Toronto&topicPath=473&latitude=43.685868&longitude=-79.487745", "", "");
        links[4] = new Links("Banks that help newcomers", "https://www.td.com/ca/en/personal-banking"
                , "https://www.cibc.com/en/personal-banking.html", "https://www.rbcroyalbank.com/personal.html", "", "");
        links[5] = new Links("Benefits", "https://www.canada.ca/en/services/benefits.html"
                , "https://www.woodgreen.org", "https://ywcacanada.ca", "", "");
        links[6] = new Links("Different seasons",
                "https://www.destinationontario.com/en-ca/travel-resources/about-ontario",
                "https://settlement.org/ontario/housing/living-in-ontario/housing-basics/what-is-the-weather-like-in-ontario/", "", "", "");

        links[7] = new Links("Recreational centers", "https://www.orfa.com"
                , "", "https://www.toronto.ca/explore-enjoy/parks-recreation/places-spaces/parks-and-recreation-facilities/parks-and-community-recreation-centres-map/#location=&lat=&lng=&zoom=", "https://www.prontario.org", "https://www.mississauga.ca/recreation-and-sports/locations-and-rentals/locations/");
        links[8] = new Links("Women's Health", "https://unityhealth.to/areas-of-care/programs-and-clinics/womens-health/"
                , "https://womenscareclinic.ca", "https://www.uhn.ca/Medicine/Womens_Health_Program/About", "", "");
        links[9] = new Links("Emergency numbers and information", "https://www.toronto.ca/home/311-toronto-at-your-service/make-the-right-call/", "", "", "", "");
        String[] emergencyNumbers = {"911", "311", "211", "811", "988", "511"};

        Scanner input = new Scanner(System.in);
        boolean running = true;

        while (running) {

            System.out.println("\n === GTA Newcomer Women Guide ===");
            System.out.println("1. View helpful websites");
            System.out.println("2. View Emergency numbers");
            System.out.println("3. Help");
            System.out.println("4. Exit");
            System.out.println("Please enter the number for the site you which to get to");
            String choice = input.nextLine();


            System.out.println("Welcome to Canada. As a newcomer we know how challenging it can be to get settled as a female. ");
            System.out.println("These are list of helpful Websites in the GTA:");


            switch (choice) {
                case "1":
                    for (Links link : links) {
                        System.out.println("\n" + link.name);
                        if (link.website1 != null) {
                            System.out.println(link.website1);
                        }
                        if (link.website2 != null) {
                            System.out.println(link.website2);
                        }
                    }
                    break;

                case "2":

                    System.out.println("Emergency and information numbers below");

                    for (String number : emergencyNumbers) {
                        System.out.println(number);
                    }
                    break;

                case "3":

                    System.out.println("Emergency numbers and information:" + links[9].website1);

                    System.out.println("\nHelp");
                    System.out.println("Enter 1 to view websites.");
                    System.out.println("Enter 2 to view emergency numbers.");
                    System.out.println("Enter 3 to view help.");
                    System.out.println("Enter 4 to exit.");

                    break;

                case "4":
                    running = false;
                    System.out.println("Thank you for using the program.");
                    break;

                default:
                    System.out.println("Invalid choice. Please enter 1, 2, 3, or 4.");
            }
            //System.out.println(links[0].name +":"+links[0].website1);
            // System.out.println("1."+links[1].name +":"+links[1].website1);

            // System.out.println("2."+links[2].name +":"+links[2].website1);
            // System.out.println("3."+links[2].name +":"+links[2].website2);

            // System.out.println("4."+links[3].name +":"+links[3].website1);
            // System.out.println("5."+links[3].name +":"+links[3].website2);
            // System.out.println("6."+links[3].name +":"+links[3].website3);

            // System.out.println("5."+links[4].name +":"+links[4].website1);
            // System.out.println("6."+links[4].name +":"+links[4].website2);
            // System.out.println("7."+links[4].name +":"+links[4].website3);

            //  System.out.println("8."+links[5].name +":"+links[5].website1);
            // System.out.println("9."+links[5].name +":"+links[5].website2);
            // System.out.println("10."+links[5].name +":"+links[5].website3);

            // System.out.println("11."+links[6].name +":"+links[6].website1);
            // System.out.println("12."+links[6].name +":"+links[6].website2);
            // System.out.println("13."+links[6].name +":"+links[6].website3);

            // System.out.println("14."+links[7].name +":"+links[7].website1);
            // System.out.println("15."+links[7].name +":"+links[7].website2);
            // System.out.println("16."+links[7].name +":"+links[7].website3);

            //  System.out.println("9."+links[8].name +" :"+links[8].website1);
            //  System.out.println("10."+links[8].name +" :"+links[8].website2);
            //System.out.println("11."+links[8].name +":"+links[8].website3);

        }

                input.close();
        }
    }






