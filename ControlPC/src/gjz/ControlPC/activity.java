package gjz.ControlPC;
import gjz.ControlPC.R;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.gesture.ContainerScrollType;
import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.view.LineChartView;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
public class activity extends Activity{

	private LineChartView lineChart;

//	String[] date = { "10-22", "11-22", "12-22", "1-22", "6-22", "5-23",
	//		"5-22", "6-22", "5-23", "5-22" };// X��ı�ע
//    float []f=new float[20000];
//	private List<PointValue> mPointValues = new ArrayList<PointValue>();
//	private List<AxisValue> mAxisXValues = new ArrayList<AxisValue>();

	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.act2);
//		lineChart = (LineChartView) findViewById(R.id.chart);
//
//		
//		getAxisPoints();// ��ȡ�����
//		initLineChart();// ��ʼ��
//		Button bt=(Button)findViewById(R.id.button1);
//		bt.setOnClickListener(new BtnListener());
	}
//	class BtnListener implements OnClickListener{
//		public void onClick(View v){
////			getAxisXLables();// ��ȡx��ı�ע
//			getAxisPoints();// ��ȡ�����
//			initLineChart();// ��ʼ��
//		}
//
//	}
	/**
	 * ����X �����ʾ
	 */
//	private void getAxisXLables() {
//		for (int i = 0; i < date.length; i++) {
//			mAxisXValues.add(new AxisValue(i).setLabel(date[i]));
//		}
//	}

	/**
	 * ͼ���ÿ�������ʾ
	 */
//	private void getAxisPoints() {
//		for (int i = 0; i < f.length; i++) {
//			mPointValues.add(new PointValue(i, f[i]));
//		}
//	}
//
//	private void initLineChart() {
//		Line line = new Line(mPointValues)
//				.setColor(Color.parseColor("#FFCD41")); // ���ߵ���ɫ����ɫ��
//		List<Line> lines = new ArrayList<Line>();
//	//	line.setShape(ValueShape.CIRCLE);// ����ͼ��ÿ�����ݵ����״ ������Բ�� ��������
//											// ��ValueShape.SQUARE
//											// ValueShape.CIRCLE
//											// ValueShape.DIAMOND��
//		line.setCubic(false);// �����Ƿ�ƽ�����������߻�������
//		line.setFilled(false);// �Ƿ�������ߵ����
//	//	line.setHasLabels(true);// ���ߵ����������Ƿ���ϱ�ע
//		// line.setHasLabelsOnlyForSelected(true);//�������������ʾ���ݣ����������line.setHasLabels(true);����Ч��
//		line.setHasLines(true);// �Ƿ�������ʾ�����Ϊfalse ��û������ֻ�е���ʾ
//		line.setHasPoints(false);// �Ƿ���ʾԲ�� ���Ϊfalse ��û��ԭ��ֻ�е���ʾ��ÿ�����ݵ㶼�Ǹ����Բ�㣩
//		lines.add(line);
//		LineChartData data = new LineChartData();
//		data.setLines(lines);
//
//		// ������
//		Axis axisX = new Axis(); // X��
//		axisX.setHasTiltedLabels(true); // X������������б����ʾ����ֱ�ģ�true��б����ʾ
//	//	axisX.setTextColor(Color.WHITE); // ����������ɫ
//		// axisX.setName("date"); //�������
//	//	axisX.setTextSize(10);// ���������С
//		//axisX.setMaxLabelChars(8); // ��༸��X�����꣬��˼�������������X�������ݵĸ���7<=x<=mAxisXValues.length
//		axisX.setValues(mAxisXValues); // ���X�����������
//		data.setAxisXBottom(axisX); // x ���ڵײ�
//		// data.setAxisXTop(axisX); //x ���ڶ���
//		axisX.setHasLines(true); // x ��ָ���
//
//		// Y���Ǹ������ݵĴ�С�Զ�����Y������(�������һ�����̶�Y�����ݸ����Ľ������)
//		Axis axisY = new Axis(); // Y��
//		axisY.setName("");// y���ע
//		axisY.setTextSize(10);// ���������С
//		data.setAxisYLeft(axisY); // Y�����������
//		// data.setAxisYRight(axisY); //y���������ұ�
//
//		// ������Ϊ���ԣ�֧�����š������Լ�ƽ��
//		//lineChart.setInteractive(true);
//		lineChart.setZoomType(ZoomType.HORIZONTAL);
//	//	lineChart.setMaxZoom((float) 2);// ��󷽷�����
//		lineChart.setContainerScrollEnabled(true,
//				ContainerScrollType.HORIZONTAL);
//		lineChart.setLineChartData(data);
//		lineChart.setVisibility(View.VISIBLE);
//		/**
//		 * ע�������7��10ֻ�Ǵ���һ������ȥ��ȶ���
//		 * ��ʱ��Ϊ�˽��X��̶����ݸ���������http://forum.xda-developers.com
//		 * /tools/programming/library
//		 * -hellocharts-charting-library-t2904456/page2��;
//		 */
//		Viewport v = new Viewport(lineChart.getMaximumViewport());
//		v.left = 0;
//		v.right = 200;
//	//	lineChart.setCurrentViewport(v);
//		lineChart.setMaximumViewport(v);
//	//	lineChart.setCurrentViewport(v);
//	}
}