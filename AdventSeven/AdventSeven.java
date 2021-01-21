
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;


public class AdventSeven {
    static Map<String, ArrayList<String>> graphMap = new HashMap<>();

    public static void main(String[] args) throws IOException {
        //System.out.println("Hello world!");
        Path fileName = Path.of("input7.txt");
        String input = Files.readString(fileName);
        int count = handleInput(input);
        System.out.println(count);
        int numBags = calculateNumBags(input.split("\n"), "shiny gold bag", 1);
        System.out.println(numBags);

    }

    static int handleInput(String input){
        int count = 0;
        Map<String, Integer> bagNum = new HashMap<>();

        String[] lines = input.split("\n");
        for(String line : lines){
            String[] bags = line.split("s contain ");
            String toadd = bags[0].replaceAll("bags", "bag");
            addToGraph(toadd);
            if(bags[1].toCharArray()[1] == 'n' ){
                System.out.println("continued");
                continue;
            }
            if(!bags[1].contains(",")){
                String node = bags[1].replaceAll("\\.","").replaceAll("bags","bag").substring(2);
                if(bags[1].substring(0,1).equals("n")){
                    bagNum.put(node, 0);
                }else{
                    Integer num = Integer.parseInt(bags[1].substring(0, 1));
                    bagNum.put(node, num);
                }
                
                System.out.println(node);
                addToNode(toadd,node);
            }else{
                System.out.println("Entered else");
                String[] containBag = bags[1].split(", ");
                for (String s : containBag) {
                    String node = s.replaceAll("\\.", "").replaceAll("bags", "bag").substring(2);
                    if(s.substring(0,1).equals("n")){
                        bagNum.put(node, 0);
                    }else{
                        Integer num = Integer.parseInt(s.substring(0, 1));
                        bagNum.put(node, num);
                    }
                    
                    System.out.println(node);
                    addToNode(toadd, node);
                }
            }
        }
        List<String> visitedBags = new ArrayList<>();
        List<String> occurrances = new ArrayList<>();
        recursiveDFS("shiny gold bag", visitedBags, occurrances);



        System.out.println(graphMap.size());
        Set<String> actualSize = new HashSet<>();
        for(String s: occurrances){
            actualSize.add(s);
        }

        //fillBagNum(bagNum);

        int sum = 0;
        /*for(String s : actualSize){
            sum = sum + bagNum.get(s);
        }*/

        System.out.println(graphMap.get("shiny gold bag").toString());
        System.out.println(bagNum.get("shiny gold bag"));
        System.out.println(bagNum.get("vibrant blue bag"));;

        System.out.println(actualSize.size());
        return sum;
    }


    static void fillBagNum(Map<String,Integer> bagNum){
        for(String s : graphMap.keySet()){
            int num = 0;
            for(String containedBags : graphMap.get(s)){
             num = num + bagNum.get(containedBags);   
            }
            bagNum.put(s, num);
        }
    }

    static int calculateNumBags(String[] lines, String toFind, int count){
        
        for(String line : lines){
            if(line.equals(toFind)){
                String[] allBags = line.split("s contain ");
                if(allBags[1].contains(",")){
                    String[] bags    = allBags[1].split(", ");
                    for(String s : bags){
                        String node = s.replaceAll("\\.", "").replaceAll("bags", "bag").substring(2);
                        if(s.substring(0,1).equals("n")){
                            return 0;
                        }else{
                            Integer num = Integer.parseInt(s.substring(0, 1));
                            count = count + num * calculateNumBags(lines, node, count);
                        }
        
                    }
                }else if(!allBags[1].contains("no other bags")){
                    String node = allBags[1].replaceAll("\\.", "").replaceAll("bags", "bag").substring(2);
                    int num = Integer.parseInt(allBags[1].substring(0, 1));
                    count = count + num * calculateNumBags(lines, node, count);
                }
                

            }
        }

        return count;
    }



    static void recursiveDFS(String v, List<String> visited, List<String> occurances){
        visited.add(v);
        for(String s : graphMap.keySet()){
            if(graphMap.get(s).contains(v)){
                occurances.add(s);
               if(!visited.contains(s)){
                   recursiveDFS(s,visited, occurances);
               }
            }
        }
    }

        public static void addToGraph(String e){
            if(graphMap.containsKey(e)){
                return;
            }
            graphMap.put(e, new ArrayList<String>());
        }

        public static void addToNode(String toAddto, String toAdd){
            graphMap.get(toAddto).add(toAdd);
        }

}
