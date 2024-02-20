import java.io.*;
import java.util.*;

public class RecordGroup {
    private ArrayList<Game_Record> records = new ArrayList<Game_Record>();

    public static RecordGroup recordGroup;

    /**
     * @return singular object of RecordGroup)
     */
    public static RecordGroup getRecordGroup() {
        if (recordGroup == null) {
            recordGroup = new RecordGroup();
        }
        return recordGroup;
    }

    public void add(Game_Record record) {
        this.records.add(record);
    }

    public ArrayList<Game_Record> getArrayList() {
        return this.records;
    }

    public void sortByTime() {
        Game_Record temp;
        int j;
        for (int i = 1; i < this.records.size(); i++) {
            temp = this.records.get(i);
            j = i - 1;
            while (j >= 0 && temp.getDate().compareTo(this.records.get(i).getDate()) > 0) {
                this.records.remove(j + 1);
                this.records.add(j + 1, this.records.get(j));
                j--;
            }
            this.records.remove(j + 1);
            this.records.add(j + 1, temp);
        }
    }

    public void sortByName() {
        Game_Record temp;
        for (int i = 0; i < this.records.size() - 1; i++) {
            for (int j = 1; j < this.records.size() - i; j++) {
                if (this.records.get(j - 1).getPlayer1().compareTo(this.records.get(j).getPlayer1()) < 0) {
                    temp = this.records.get(j);
                    this.records.remove(j);
                    this.records.add(j, this.records.get(j - 1));
                    this.records.remove(j - 1);
                    this.records.add(j - 1, temp);
                }
            }
        }
    }

    /**
     * Search by the name
     * 
     * @param name player1 name or player 2 name
     * @return ArrayList of Game_Records which have same name with user input
     */
    public ArrayList<Game_Record> searchByName(String name) {
        ArrayList<Game_Record> searchedRecords = new ArrayList<Game_Record>();
        return searchByName(searchedRecords, name, 0);
    }

    /**
     * Recursion method
     * 
     * @param searchedRecords ArrayList of Game_Records which have same name with
     *                        user input
     * @param name            user input
     * @param i               how many times looped
     * @return recursion or ArrayList
     */
    public ArrayList<Game_Record> searchByName(ArrayList<Game_Record> searchedRecords,
            String name, int i) {
        if (i >= records.size()) {
            return searchedRecords;
        } else if (records.get(i).getPlayer1().equals(name) || records.get(i).getPlayer2().equals(name)) {
            searchedRecords.add(records.get(i));
        }
        return searchByName(searchedRecords, name, i++);
    }

    /**
     * search index of the Game_Record which have same code with given code
     * 
     * @param code given code
     * @return return index of searched Game_Record, -1 if not found
     */
    public int searchByCode(String code) {
        for (int i = 0; i < records.size(); i++) {
            if (records.get(i).getCode().equals(code)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * delete selected Game_Record from records
     * 
     * @param code user input
     * @return true if success
     */
    public boolean deleteGame(String code) {
        for (int i = 0; i < records.size(); i++) {
            if (records.get(i).getCode().equals(code)) {
                records.remove(i);
                return true;
            }
        }
        return false;
    }

    /**
     * save one Game_Record to file
     * 
     * @param record save this Game_Record
     * @return true if success
     */
    public boolean saveGame(Game_Record record, String fileName) {
        try {
            BufferedWriter wr = new BufferedWriter(new FileWriter(fileName, true));
            wr.newLine();
            wr.newLine();
            wr.write(record.getWin() + "");
            wr.newLine();
            wr.write(record.getDate());
            wr.newLine();
            wr.write(record.getPlayer1());
            wr.newLine();
            wr.write(record.getPlayer2());
            wr.newLine();
            wr.write(record.getCode());
            wr.newLine();
            wr.write(record.getTurn() + "");
            wr.newLine();
            wr.write(record.getNumMoved() + "");
            for (int i = 0; i < record.getNumMoved() * 4; i += 4) {
                wr.newLine();
                wr.write(record.movement.get(i) + ", " + record.movement.get(i + 1));
                wr.newLine();
                wr.write(record.movement.get(i + 2) + ", " + record.movement.get(i + 3));
            }
            wr.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * save Whole game into file
     * 
     * @return true if success
     */
    public boolean fileSave(String fileName) {
        try {
            BufferedWriter wr = new BufferedWriter(new FileWriter(fileName, true));
            for (int i = 0; i < records.size(); i++) {
                saveGame(records.get(i), fileName);
            }
            for (int i = 0; i < records.size(); i++) {
                for (int j = 0; j < records.size(); j++) {
                    if (records.get(i).getCode()
                            .equals(records.get(j).getCode())) {
                        deleteGame(records.get(i).getCode());
                    }
                }

            }
            wr.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * read file and save it at records
     * 
     * @return true if success
     */
    public boolean fileLoad(String fileName) {
        try {
            BufferedReader rd = new BufferedReader(new FileReader(fileName));
            while (rd.readLine() != null) {
                Game_Record newGame = new Game_Record("", "", 0, 0, records);
                int numMoved;
                newGame.setWin(Integer.parseInt(rd.readLine()));
                newGame.setDate(rd.readLine());
                newGame.setPlayer1(rd.readLine());
                newGame.setPlayer2(rd.readLine());
                newGame.setCode(rd.readLine());
                newGame.setTurn(Integer.parseInt(rd.readLine()));
                numMoved = Integer.parseInt(rd.readLine());
                newGame.setNumMoved(numMoved);
                int[] movement = new int[numMoved * 4];
                for (int i = 0; i < movement.length; i += 4) {
                    String a = rd.readLine();
                    String b = rd.readLine();
                    movement[i] = Integer.parseInt(a.substring(0, 1));
                    movement[i + 1] = Integer.parseInt(a.substring(3, 4));
                    movement[i + 2] = Integer.parseInt(b.substring(0, 1));
                    movement[i + 3] = Integer.parseInt(b.substring(3, 4));
                }
                for (int i = 0; i < movement.length; i++) {
                    newGame.movement.add(movement[i]);
                }
                for (int i = 0; i < movement.length; i += 4) {
                    newGame.move(newGame.movement.get(i), newGame.movement.get(i + 1), newGame.movement.get(i + 2),
                            newGame.movement.get(i + 3));
                }
                newGame.setPieces();
                this.records.add(newGame);
            }
            rd.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}