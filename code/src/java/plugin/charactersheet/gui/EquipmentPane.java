/*
 * EquipmentPane.java
 *
 * Created on February 22, 2004, 9:39 PM
 */

package plugin.charactersheet.gui;

import pcgen.core.*;
import pcgen.io.exporttoken.EqToken;
import pcgen.io.exporttoken.TotalToken;
import pcgen.util.BigDecimalHelper;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Component;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author  soulcatcher
 */
public class EquipmentPane extends JPanel {
	private PlayerCharacter pc;
	private List<Component> removeList = new ArrayList<Component>();

  private JPanel costHeaderPanel;
  private JPanel equipmentHeaderPanel;
  private JPanel itemHeaderPanel;
  private JPanel locationHeaderPanel;
  private JPanel qtyHeaderPanel;
  private JPanel wtHeaderPanel;
	private JLabel totalWeight = new JLabel();
	private JLabel totalGp = new JLabel();

	private static final String DIALOG = "Dialog";
	private static final String BLANK = "";
	private static final String SPACE = " ";
	private static final String TOTAL_VALUE = "TOTAL WEIGHT CARRIED/VALUE";
	private static final String EQUIPMENT = "Equipment";
	private static final String ITEM = "ITEM";
	private static final String LOCATION = "LOCATION";
	private static final String QTY = "QTY";
	private static final String COST = "COST";
	private static final String WT = "WT";
	private static final String COIN = "Coin";
	private static final String GEM = "Gem";
	private static final String AMMO = "Ammunition";
	private static final String POTION = "Potion";
	private static final String CONSUMABLE = "Consumable";

	/** Creates new form EquipmentPane */
	public EquipmentPane() {
		initComponents();
		setLocalColor();
	}

	/** This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
  private void initComponents() {//GEN-BEGIN:initComponents
    java.awt.GridBagConstraints gridBagConstraints;


    equipmentHeaderPanel = new JPanel();
    itemHeaderPanel = new JPanel();
    costHeaderPanel = new JPanel();
    locationHeaderPanel = new JPanel();
    qtyHeaderPanel = new JPanel();
    wtHeaderPanel = new JPanel();

    setLayout(new java.awt.GridBagLayout());

    JLabel equipmentLabel = new JLabel();
    equipmentHeaderPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 1, 0));
    equipmentLabel.setFont(new java.awt.Font(DIALOG, 1, 14));
    equipmentLabel.setText(EQUIPMENT);
    equipmentHeaderPanel.add(equipmentLabel);
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridwidth = 5;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    add(equipmentHeaderPanel, gridBagConstraints);

    JLabel itemLabel = new JLabel();
    itemHeaderPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 1, 0));
    itemLabel.setFont(new java.awt.Font(DIALOG, 0, 10));
    itemLabel.setText(ITEM);
    itemHeaderPanel.add(itemLabel);
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    add(itemHeaderPanel, gridBagConstraints);

    JLabel locationLabel = new JLabel();
    locationHeaderPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 1, 0));
    locationLabel.setFont(new java.awt.Font(DIALOG, 0, 10));
    locationLabel.setText(LOCATION);
    locationHeaderPanel.add(locationLabel);
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    add(locationHeaderPanel, gridBagConstraints);

    JLabel qtyLabel = new JLabel();
    qtyHeaderPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 1, 0));
    qtyLabel.setFont(new java.awt.Font(DIALOG, 0, 10));
    qtyLabel.setText(QTY);
    qtyHeaderPanel.add(qtyLabel);
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 2;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    add(qtyHeaderPanel, gridBagConstraints);

    JLabel costLabel = new JLabel();
    costHeaderPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 1, 0));
    costLabel.setFont(new java.awt.Font(DIALOG, 0, 10));
    costLabel.setText(COST);
    costHeaderPanel.add(costLabel);
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 4;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    add(costHeaderPanel, gridBagConstraints);

    JLabel wtLabel = new JLabel();
    wtHeaderPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 1, 0));
    wtLabel.setFont(new java.awt.Font(DIALOG, 0, 10));
    wtLabel.setText(WT);
    wtHeaderPanel.add(wtLabel);
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 3;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    add(wtHeaderPanel, gridBagConstraints);

    JLabel bufferLabel = new JLabel();
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.insets = new java.awt.Insets(0, 200, 0, 0);
    add(bufferLabel, gridBagConstraints);

  }//GEN-END:initComponents

	/**
	 * Set color
	 */
  	public void setColor() {
		setLocalColor();
		refresh();
	}

  	/**
  	 * Set local color
  	 */
	public void setLocalColor() {
		setBackground(CharacterPanel.white);
		setBorder(new LineBorder(CharacterPanel.border));
		equipmentHeaderPanel.setBackground(CharacterPanel.header);
		itemHeaderPanel.setBackground(CharacterPanel.header);
		locationHeaderPanel.setBackground(CharacterPanel.header);
		qtyHeaderPanel.setBackground(CharacterPanel.header);
		costHeaderPanel.setBackground(CharacterPanel.header);
		wtHeaderPanel.setBackground(CharacterPanel.header);
	}

	/**
	 * Set PC
	 * @param pc
	 */
	public void setPc(PlayerCharacter pc) {
		this.pc = pc;
	}

	/**
	 * Refresh
	 */
	public void refresh() {
		List<Equipment> eqList = getEqList(pc);
		if(eqList.size() == 0) {
			setVisible(false);
		}
		else {
			setVisible(true);
			for (Component c : removeList) {
				remove(c);
			}
			removeList.clear();

			int gridY = 2;
			for(int i = 0; i < eqList.size(); i++) {
				Color color;
				if(i % 2 == 0) {
					color = CharacterPanel.bodyLight;
				}
				else {
					color = CharacterPanel.bodyMedLight;
				}
				Equipment eq = eqList.get(i);
				gridY = addLine(eq, color, gridY);
			}
			addEnd(gridY, TotalToken.getWeightToken(pc), TotalToken.getValueToken(pc));
		}
	}

	private int addLine(Equipment eq, Color color, int gridY) {
		java.awt.GridBagConstraints gridBagConstraints = new java.awt.GridBagConstraints();

		JTextArea item = new JTextArea();
		item.setLineWrap(true);
		item.setWrapStyleWord(true);
		item.setBackground(color);
		int bold = 0;
		if(eq.isEquipped()) {
			bold = 1;
		}
		item.setFont(new java.awt.Font(DIALOG, bold, 11));
		item.setText(EqToken.getLongNameToken(eq));
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = gridY;
		gridBagConstraints.gridheight = 2;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		add(item, gridBagConstraints);
		removeList.add(item);

		JPanel locationPanel = new JPanel();
		JLabel location = new JLabel();
		locationPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 1, 0));
		locationPanel.setBackground(color);
		location.setFont(new java.awt.Font(DIALOG, 0, 10));
		location.setText(EqToken.getLocationToken(eq));
		locationPanel.add(location);
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = gridY;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		add(locationPanel, gridBagConstraints);
		removeList.add(locationPanel);

		JPanel qtyPanel = new JPanel();
		JLabel qty = new JLabel();
		qtyPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 1, 0));
		qtyPanel.setBackground(color);
		qty.setFont(new java.awt.Font(DIALOG, 0, 10));
		qty.setText(EqToken.getQtyToken(eq));
		qtyPanel.add(qty);
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 2;
		gridBagConstraints.gridy = gridY;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		add(qtyPanel, gridBagConstraints);
		removeList.add(qtyPanel);

		JPanel wtPanel = new JPanel();
		JLabel wt = new JLabel();
		wtPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 1, 0));
		wtPanel.setBackground(color);
		wt.setFont(new java.awt.Font(DIALOG, 0, 10));
		wt.setText(Globals.getGameModeUnitSet().displayWeightInUnitSet(EqToken.getWtTokenDouble(pc, eq)));
		wtPanel.add(wt);
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 3;
		gridBagConstraints.gridy = gridY;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		add(wtPanel, gridBagConstraints);
		removeList.add(wtPanel);

		JPanel costPanel = new JPanel();
		JLabel cost = new JLabel();
		costPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 1, 0));
		costPanel.setBackground(color);
		cost.setFont(new java.awt.Font(DIALOG, 0, 10));
		cost.setText(EqToken.getCostToken(pc, eq));
		costPanel.add(cost);
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 4;
		gridBagConstraints.gridy = gridY;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		add(costPanel, gridBagConstraints);
		removeList.add(costPanel);
		gridY++;

		JPanel panel1 = new JPanel();
		panel1.setBackground(color);
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = gridY;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		add(panel1, gridBagConstraints);
		removeList.add(panel1);

		JPanel panel2 = new JPanel();
		panel2.setBackground(color);
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 2;
		gridBagConstraints.gridy = gridY;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		add(panel2, gridBagConstraints);
		removeList.add(panel2);

		JPanel totalWtPanel = new JPanel();
		totalWtPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 1, 0));
		totalWtPanel.setBackground(color);
		if(eq.qty() > 1.0) {
			JLabel totalWt = new JLabel();
			totalWt.setFont(new java.awt.Font(DIALOG, 0, 10));
			//TODO: move in to the EqToken
			StringBuffer sb = new StringBuffer();
			sb.append('(').append(Globals.getGameModeUnitSet().displayWeightInUnitSet(eq.qty() * eq.getWeightAsDouble(pc))).append(')');
			totalWt.setText(sb.toString());
			totalWtPanel.add(totalWt);
		}
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 3;
		gridBagConstraints.gridy = gridY;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		add(totalWtPanel, gridBagConstraints);
		removeList.add(totalWtPanel);

		JPanel totalCostPanel = new JPanel();
		totalCostPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 1, 0));
		totalCostPanel.setBackground(color);
		if(eq.qty() > 1.0) {
			JLabel totalCost = new JLabel();
			totalCost.setFont(new java.awt.Font(DIALOG, 0, 10));
			//TODO: move in to the EqToken
			BigDecimal bdTotalCost = eq.getCost(pc).multiply(new BigDecimal(eq.qty()));
			StringBuffer sb = new StringBuffer();
			sb.append('(').append(BigDecimalHelper.trimZeros(bdTotalCost)).append(')');
			totalCost.setText(sb.toString());
			totalCostPanel.add(totalCost);
		}
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 4;
		gridBagConstraints.gridy = gridY;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		add(totalCostPanel, gridBagConstraints);
		removeList.add(totalCostPanel);
		gridY++;


		String contentsText = EqToken.getContentsToken(eq);
		if(!contentsText.equals(BLANK)) {
			gridBagConstraints = new java.awt.GridBagConstraints();
			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = gridY;
			gridBagConstraints.gridwidth = 5;
			gridBagConstraints.gridheight = 1;
			gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
			JTextArea contents = new JTextArea();
			contents.setLineWrap(true);
			contents.setWrapStyleWord(true);
			contents.setBackground(color);
			contents.setFont(new java.awt.Font(DIALOG, 0, 9));
			contents.setText(contentsText);
			add(contents, gridBagConstraints);
			removeList.add(contents);
			gridY++;
		}

		String spropText = EqToken.getSpropToken(pc, eq);
		if(!spropText.equals(BLANK)) {
			gridBagConstraints = new java.awt.GridBagConstraints();
			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = gridY;
			gridBagConstraints.gridwidth = 5;
			gridBagConstraints.gridheight = 1;
			gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
			JTextArea sprop = new JTextArea();
			sprop.setLineWrap(true);
			sprop.setWrapStyleWord(true);
			sprop.setBackground(color);
			sprop.setFont(new java.awt.Font(DIALOG, 0, 9));
			sprop.setText(spropText);
			add(sprop, gridBagConstraints);
			removeList.add(sprop);
			gridY++;
		}

		String noteText = EqToken.getNoteToken(eq);
		if(!noteText.equals(BLANK)) {
			gridBagConstraints = new java.awt.GridBagConstraints();
			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = gridY;
			gridBagConstraints.gridwidth = 5;
			gridBagConstraints.gridheight = 1;
			gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
			JTextArea note = new JTextArea();
			note.setLineWrap(true);
			note.setWrapStyleWord(true);
			note.setBackground(color);
			note.setFont(new java.awt.Font(DIALOG, 0, 9));
			note.setText(noteText);
			add(note, gridBagConstraints);
			removeList.add(note);
			gridY++;
		}

		int boxes = getBoxes(eq);
		if(boxes > 0) {
			JPanel panel = new JPanel();
			for(int i = 0; i < boxes; i++) {
				if(i % 25 == 0) {
					panel = new JPanel();
					panel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 0, 1));
					panel.setBackground(color);
					gridBagConstraints = new java.awt.GridBagConstraints();
					gridBagConstraints.gridx = 0;
					gridBagConstraints.gridy = gridY;
					gridBagConstraints.gridwidth = 5;
					gridBagConstraints.gridheight = 1;
					gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
					add(panel, gridBagConstraints);
					removeList.add(panel);
					gridY++;
				}
				if(i % 5 == 0) {
					JLabel bufLabel = new JLabel();
					bufLabel.setFont(new java.awt.Font(DIALOG, 0, 10));
					bufLabel.setText(SPACE);
					panel.add(bufLabel);
				}
				JCheckBox checkBox = new JCheckBox();
				checkBox.setBackground(color);
				checkBox.setBorder(null);
				panel.add(checkBox);
			}
		}
		return gridY;
	}


	private void addEnd(int gridY, String weight, String gp) {
		JPanel totalValuePanel = new JPanel();
		totalValuePanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 1, 0));
		totalValuePanel.setBackground(CharacterPanel.header);
		JLabel totalValue = new JLabel();
		totalValue.setFont(new java.awt.Font(DIALOG, 0, 10));
		totalValue.setText(TOTAL_VALUE);
		totalValuePanel.add(totalValue);
		java.awt.GridBagConstraints gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = gridY;
		gridBagConstraints.gridwidth = 3;
		gridBagConstraints.gridheight = 2;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		add(totalValuePanel, gridBagConstraints);
		removeList.add(totalValuePanel);

		JPanel totalWeightPanel = new JPanel();
		totalWeightPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 1, 0));
		totalWeightPanel.setBackground(CharacterPanel.header);
		totalWeight.setFont(new java.awt.Font(DIALOG, 0, 10));
		totalWeight.setText(weight);
		totalWeightPanel.add(totalWeight);
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 3;
		gridBagConstraints.gridy = gridY;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		add(totalWeightPanel, gridBagConstraints);
		removeList.add(totalWeightPanel);

		JPanel totalGpPanel = new JPanel();
		totalGpPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 1, 0));
		totalGpPanel.setBackground(CharacterPanel.header);
		totalGp.setFont(new java.awt.Font(DIALOG, 0, 10));
		totalGp.setText(gp);
		totalGpPanel.add(totalGp);
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 4;
		gridBagConstraints.gridy = gridY;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		add(totalGpPanel, gridBagConstraints);
		removeList.add(totalGpPanel);
	}

	private List<Equipment> getEqList(PlayerCharacter aPC) {
		int merge = Constants.MERGE_LOCATION;
		List<Equipment> eqList = aPC.getEquipmentListInOutputOrder(merge);
		eqList = EquipmentUtilities.removeEqType(eqList, COIN);
		eqList = EquipmentUtilities.removeEqType(eqList, GEM);
		return eqList;
	}

	private int getBoxes(Equipment eq) {
		int num = 0;
		//TODO (DJ) remove ammo and potion from this when the data is up to date
		if(eq.isType(AMMO) || eq.isType(POTION) || eq.isType(CONSUMABLE)) {
			num = (int)EqToken.getQtyDoubleToken(eq);
		}
		else if(eq.getRemainingCharges() > 0) {
			num = EqToken.getChargesTokenInt(eq);
		}
		return num;
	}

	/**
	 * Destroy
	 */
	public void destruct() {
		//Put any code here that is needed to prevent memory leaks when this panel is destroyed
	}
}
