package main.games.minefield.model;

import java.awt.Color;

import main.games.minefield.view.ViewField;
import main.dashboard.view.EndGame;
import main.games.minefield.controller.Cell;
import main.games.minefield.controller.Field;
import main.games.minefield.controller.MinefieldController;

import java.util.ArrayList;
public class Handler  {

    //array to know where is the player.
    private ArrayList<Cell> now = new ArrayList<Cell>();
    private ArrayList<Cell> queue = new ArrayList<Cell>();

    private static int putFlag = 0;

    /**
     * method that discovers the cells.
     * Remider: 0-empty, 1-mine, 2-flag
     * @param cell to know what cell is used
     */
    public void click(final Cell cell) {
        int discovered = 0;
        if (!cell.isFlagged()) {
            cell.setEnabled(false);
            cell.setDiscovered(true);

            int position = cell.getPosition();
            if (cell.getType() == 0) {
                if (position < ViewField.getGridSize()) {
                    if (position % ViewField.getGridSize() == 0) {
                        queue.add(Field.getCell().get((position + ViewField.getGridSize())));
                        queue.add(Field.getCell().get((position + ViewField.getGridSize() + 1)));
                        queue.add(Field.getCell().get((position + 1)));
                    } else if (position % ViewField.getGridSize() == ViewField.getGridSize() - 1) {
                        queue.add(Field.getCell().get((position + ViewField.getGridSize())));
                        queue.add(Field.getCell().get((position + ViewField.getGridSize() - 1)));
                        queue.add(Field.getCell().get((position - 1)));
                    } else {
                        queue.add(Field.getCell().get((position + ViewField.getGridSize())));
                        queue.add(Field.getCell().get((position + ViewField.getGridSize() + 1)));
                        queue.add(Field.getCell().get((position + ViewField.getGridSize() - 1)));
                        queue.add(Field.getCell().get((position + 1)));
                        queue.add(Field.getCell().get((position - 1)));
                    }
                } else if (position >= (ViewField.getGridSize() * (ViewField.getGridSize() - 1))) {
                    if (position % ViewField.getGridSize() == 0) {
                        queue.add(Field.getCell().get((position - ViewField.getGridSize())));
                        queue.add(Field.getCell().get((position - ViewField.getGridSize() + 1)));
                        queue.add(Field.getCell().get((position + 1)));
                    } else if (position % ViewField.getGridSize() == ViewField.getGridSize() - 1) {
                        queue.add(Field.getCell().get((position - ViewField.getGridSize())));
                        queue.add(Field.getCell().get((position - ViewField.getGridSize() - 1)));
                        queue.add(Field.getCell().get((position - 1)));
                    } else {
                        queue.add(Field.getCell().get((position - ViewField.getGridSize())));
                        queue.add(Field.getCell().get((position - ViewField.getGridSize() + 1)));
                        queue.add(Field.getCell().get((position - ViewField.getGridSize() - 1)));
                        queue.add(Field.getCell().get((position + 1)));
                        queue.add(Field.getCell().get((position - 1)));
                    }
                } else if (position % ViewField.getGridSize() == 0) {
                    queue.add(Field.getCell().get((position - ViewField.getGridSize())));
                    queue.add(Field.getCell().get((position + ViewField.getGridSize())));
                    queue.add(Field.getCell().get((position - ViewField.getGridSize() + 1)));
                    queue.add(Field.getCell().get((position + ViewField.getGridSize() + 1)));
                    queue.add(Field.getCell().get((position + 1)));
                } else if (position % ViewField.getGridSize() == ViewField.getGridSize() - 1) {
                    queue.add(Field.getCell().get((position - ViewField.getGridSize())));
                    queue.add(Field.getCell().get((position + ViewField.getGridSize())));
                    queue.add(Field.getCell().get((position - ViewField.getGridSize() - 1)));
                    queue.add(Field.getCell().get((position + ViewField.getGridSize() - 1)));
                    queue.add(Field.getCell().get((position - 1)));
                } else {
                    queue.add(Field.getCell().get((position - ViewField.getGridSize())));
                    queue.add(Field.getCell().get((position + ViewField.getGridSize())));
                    queue.add(Field.getCell().get((position - ViewField.getGridSize() - 1)));
                    queue.add(Field.getCell().get((position + ViewField.getGridSize() - 1)));
                    queue.add(Field.getCell().get((position - ViewField.getGridSize() + 1)));
                    queue.add(Field.getCell().get((position + ViewField.getGridSize() + 1)));
                    queue.add(Field.getCell().get((position - 1)));
                    queue.add(Field.getCell().get((position + 1)));
                }
            } else if (cell.getType() == 2) {
                //i see if there is a mine near the cell
                int dangerCount = 0;
                if (position < ViewField.getGridSize()) {
                    if (position % ViewField.getGridSize() == 0) {
                        if (Field.getCell().get(position + ViewField.getGridSize()).getType() == 1) { 
                            dangerCount++; 
                            }
                        if (Field.getCell().get(position + ViewField.getGridSize() + 1).getType() == 1) { 
                            dangerCount++; 
                            } 
                        if (Field.getCell().get(position + 1).getType() == 1) { 
                            dangerCount++; 
                            }
                    } else if (position % ViewField.getGridSize() == ViewField.getGridSize() - 1) {
                        if (Field.getCell().get(position + ViewField.getGridSize()).getType() == 1) { 
                            dangerCount++; 
                            }
                        if (Field.getCell().get(position + ViewField.getGridSize() - 1).getType() == 1) { 
                            dangerCount++; 
                            }
                        if (Field.getCell().get(position - 1).getType() == 1) { 
                            dangerCount++; 
                            }
                    } else {
                        if (Field.getCell().get(position + ViewField.getGridSize()).getType() == 1) { 
                            dangerCount++; 
                            }
                        if (Field.getCell().get(position + ViewField.getGridSize() + 1).getType() == 1) { 
                            dangerCount++; 
                            }
                        if (Field.getCell().get(position + ViewField.getGridSize() - 1).getType() == 1) { 
                            dangerCount++; 
                            }
                        if (Field.getCell().get(position + 1).getType() == 1) { 
                            dangerCount++; 
                            }
                        if (Field.getCell().get(position - 1).getType() == 1) { 
                            dangerCount++; 
                            }
                        System.out.println(dangerCount);
                    }
                } else if (position >= (ViewField.getGridSize() * (ViewField.getGridSize() - 1))) {
                    if (position % ViewField.getGridSize() == 0) {
                        if (Field.getCell().get(position - ViewField.getGridSize()).getType() == 1) { 
                            dangerCount++; 
                            }
                        if (Field.getCell().get(position - ViewField.getGridSize() + 1).getType() == 1) { 
                            dangerCount++; 
                            }
                        if (Field.getCell().get(position + 1).getType() == 1) { 
                            dangerCount++; 
                        }
                    } else if (position % ViewField.getGridSize() == ViewField.getGridSize() - 1) {
                        if (Field.getCell().get(position - ViewField.getGridSize()).getType() == 1) {
                            dangerCount++; 
                            }
                        if (Field.getCell().get(position - ViewField.getGridSize() - 1).getType() == 1) { 
                            dangerCount++; 
                            }
                        if (Field.getCell().get(position - 1).getType() == 1) { 
                            dangerCount++; 
                            }
                    } else {
                        if (Field.getCell().get(position - ViewField.getGridSize()).getType() == 1) { 
                            dangerCount++; 
                            }
                        if (Field.getCell().get(position - ViewField.getGridSize() + 1).getType() == 1) { 
                            dangerCount++; 
                            }
                        if (Field.getCell().get(position - ViewField.getGridSize() - 1).getType() == 1) { 
                            dangerCount++; 
                            }
                        if (Field.getCell().get(position + 1).getType() == 1) { 
                            dangerCount++; 
                            }
                        if (Field.getCell().get(position - 1).getType() == 1) { 
                            dangerCount++; 
                            }
                    }
                } else if (position % ViewField.getGridSize() == 0) {
                    if (Field.getCell().get(position - ViewField.getGridSize()).getType() == 1) { 
                        dangerCount++; 
                        }
                    if (Field.getCell().get(position + ViewField.getGridSize()).getType() == 1) { 
                        dangerCount++; 
                        }
                    if (Field.getCell().get(position - ViewField.getGridSize() + 1).getType() == 1) { 
                        dangerCount++; 
                        }
                    if (Field.getCell().get(position + ViewField.getGridSize() + 1).getType() == 1) { 
                        dangerCount++; 
                        }
                    if (Field.getCell().get(position + 1).getType() == 1) { 
                        dangerCount++; 
                        }
                } else if (position % ViewField.getGridSize() == ViewField.getGridSize() - 1) {
                    if (Field.getCell().get((position - ViewField.getGridSize())).getType() == 1) {
                        dangerCount++; 
                        }
                    if (Field.getCell().get((position + ViewField.getGridSize())).getType() == 1) { 
                        dangerCount++; 
                        }
                    if (Field.getCell().get((position - ViewField.getGridSize() - 1)).getType() == 1) { 
                        dangerCount++; 
                        }
                    if (Field.getCell().get((position + ViewField.getGridSize() - 1)).getType() == 1) { 
                        dangerCount++; 
                        }
                    if (Field.getCell().get((position - 1)).getType() == 1) { 
                        dangerCount++; 
                        }
                } else {
                    if (Field.getCell().get((position - ViewField.getGridSize())).getType() == 1) { 
                        dangerCount++; 
                        }
                    if (Field.getCell().get((position + ViewField.getGridSize())).getType() == 1) { 
                        dangerCount++; 
                        }
                    if (Field.getCell().get((position - ViewField.getGridSize() - 1)).getType() == 1) { 
                        dangerCount++; 
                        }
                    if (Field.getCell().get((position + ViewField.getGridSize() - 1)).getType() == 1) { 
                        dangerCount++; 
                        }
                    if (Field.getCell().get((position - ViewField.getGridSize() + 1)).getType() == 1) { 
                        dangerCount++; 
                        }
                    if (Field.getCell().get((position + ViewField.getGridSize() + 1)).getType() == 1) { 
                        dangerCount++; 
                        }
                    if (Field.getCell().get((position - 1)).getType() == 1) { 
                        dangerCount++; 
                        }
                    if (Field.getCell().get((position + 1)).getType() == 1) { 
                        dangerCount++; 
                        }
                }
                cell.setText(String.valueOf(dangerCount));
            } else if (cell.getType() == 1) {
                for (int x = 0; x < Field.getCell().size(); x++) {
                    Field.getCell().get(x).setEnabled(false);
                    Field.getCell().get(x).setText("");
                    if (Field.getCell().get(x).getType() == 1) {
                        Field.getCell().get(x).setText("M");
                        Field.getCell().get(x).setBackground(Color.red); 
                    }
                    ViewField.getFrame().setVisible(false);
                }
                cell.setText("M");
                cell.setBackground(Color.red);
                //this.controller.EndGame(false);
            }

            for (int x = 0; x < queue.size(); x++) {
                if (!queue.get(x).isDiscovered()) {
                    now.add(queue.get(x));
                    queue.get(x).setDiscovered(true);
                }
            }
            queue.clear();
            while (!now.isEmpty()) {
                Cell temp = now.get(0);
                now.remove(0);
                temp.clickButton();
            }

            for (int x = 0; x < Field.getCell().size(); x++) {
                 if (Field.getCell().get(x).isDiscovered()) {
                    discovered++;
                   // if (discovered == StartGame.GRIDSIZE * StartGame.GRIDSIZE) {
                        //status(true);
                       //show();
                    //}
                 }
            }

            if (discovered == Field.getCell().size() - ViewField.getGridSize()) {
                for (int x = 0; x < Field.getCell().size(); x++) {
                    if (Field.getCell().get(x).getType() == 1) {
                        Field.getCell().get(x).setEnabled(false);
                        Field.getCell().get(x).setText("M");
                        Field.getCell().get(x).setBackground(Color.red);
                        ViewField.getFrame().setVisible(false);
                    } else {
                        Field.getCell().get(x).setEnabled(false);
                        Field.getCell().get(x).setText("");
                    }
                }
            }
        }
    }

    /**
     * method that puts the flag in the cell.
     * @param cell sets which cell is flagged.
     */
    public void rightClick(final Cell cell) {
        if (!cell.isDiscovered()) {
            if (!cell.isFlagged()) {
                cell.setFlagged(true);
                cell.setText("F");
                putFlag++;
                ViewField.update(putFlag);
            } else {
                cell.setFlagged(false);
                cell.setText("");
                putFlag--;
                ViewField.update(putFlag);
            }
        }
    }
}
