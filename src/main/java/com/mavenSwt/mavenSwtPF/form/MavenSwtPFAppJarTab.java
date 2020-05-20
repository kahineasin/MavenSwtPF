package com.mavenSwt.mavenSwtPF.form;

import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Text;

import com.mavenSwt.mavenSwtPF.model.PFSpringModel;

public class MavenSwtPFAppJarTab {
	private PFSpringModel _springModel;
	private Button _rebootBtn;
	private CTabItem _tabItemTmp;
	private Text _outText;
	private Text _errText;
	public MavenSwtPFAppJarTab(PFSpringModel springModel,CTabItem _tabItemTmp,Button _rebootBtn,  Text _outText, Text _errText) {
		super();
		this._springModel = springModel;
		this._tabItemTmp = _tabItemTmp;
		this._rebootBtn = _rebootBtn;
		this._outText = _outText;
		this._errText = _errText;
	}
	public Button getRebootBtn() {
		return _rebootBtn;
	}
	public CTabItem getTabItemTmp() {
		return _tabItemTmp;
	}
	public Text getOutText() {
		return _outText;
	}
	public Text getErrText() {
		return _errText;
	}

	public PFSpringModel getSpringModel() {
		return _springModel;
	}

}
