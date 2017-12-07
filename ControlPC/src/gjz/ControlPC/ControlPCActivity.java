package gjz.ControlPC;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import android.R.string;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ControlPCActivity extends Activity {
	/** Called when the activity is first created. */

	private Button startButton;
	private EditText IPText;
	private Button sendButtonClient;
	private EditText editMsgTextCilent;
	private TextView recvText;
	private Context mContext;
	private boolean isConnecting = false;

	private Thread mThreadClient = null;
	private Socket mSocketClient = null;
	static BufferedReader mBufferedReaderServer = null;
	static PrintWriter mPrintWriterServer = null;
	static BufferedReader mBufferedReaderClient = null;
	static PrintWriter mPrintWriterClient = null;
	private String recvMessageClient = "";
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		mContext = this;

		IPText = (EditText) findViewById(R.id.IPText);
		// IPText.setText("10.0.2.15:");
		IPText.setText("192.168.1.249:5000");
		startButton = (Button) findViewById(R.id.StartConnect);
		startButton.setOnClickListener(StartClickListener);
		Button btn=(Button)findViewById(R.id.tiaozhun);
        btn.setOnClickListener(BtntiaozhuanListener);	
		
		

		editMsgTextCilent = (EditText) findViewById(R.id.clientMessageText);
		editMsgTextCilent.setText("up");

		sendButtonClient = (Button) findViewById(R.id.SendButtonClient);
		sendButtonClient.setOnClickListener(SendClickListenerClient);

		recvText = (TextView) findViewById(R.id.RecvText);
		recvText.setMovementMethod(ScrollingMovementMethod.getInstance());
	}
	private OnClickListener BtntiaozhuanListener = new OnClickListener() {
		public void onClick(View v){
			Intent intent=new Intent();
			intent.setClass(ControlPCActivity.this, activity.class);
			ControlPCActivity.this.startActivity(intent);
		}
	};
	private OnClickListener StartClickListener = new OnClickListener() {
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			if (isConnecting) {
				isConnecting = false;
				try {
					if (mSocketClient != null) {
						mSocketClient.close();
						mSocketClient = null;

						mPrintWriterClient.close();
						mPrintWriterClient = null;
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				mThreadClient.interrupt();

				startButton.setText("开始连接");
				IPText.setEnabled(true);
				recvText.setText("信息:\n");
			} else {
				isConnecting = true;
				startButton.setText("停止连接");
				IPText.setEnabled(false);

				mThreadClient = new Thread(mRunnable);
				mThreadClient.start();
			}
		}
	};

	public long getUint32(long l) {
		return l & 0x00000000ffffffff;
	}

	public int getUint16(int i) {
		return i & 0x0000ffff;
	}

	private OnClickListener SendClickListenerClient = new OnClickListener() {
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			if (isConnecting && mSocketClient != null) {

				byte c[] = SendStartMsg.Start();
				if (c.length <= 0) {
					Toast.makeText(mContext, "发送内容不能为空！", Toast.LENGTH_SHORT)
							.show();
				} else {
					try {
						OutputStream out = mSocketClient.getOutputStream();
						out.write(c);
						// mPrintWriterClient.print(c);
						// mPrintWriterClient.print(x);
						// mPrintWriterClient.print(msgText);// 发送给服务器
						mPrintWriterClient.flush();
					} catch (Exception e) {
						// TODO: handle exception
						Toast.makeText(mContext, "发送异常：" + e.getMessage(),
								Toast.LENGTH_SHORT).show();
					}
				}
			} else {

				Toast.makeText(mContext, "没有连接", Toast.LENGTH_SHORT).show();
			}
		}
	};

	
	public static float []f=new float [20000];
	public static String bytesToHexString(byte[] src) {
		StringBuilder stringBuilder = new StringBuilder("");
		if (src == null || src.length <= 0) {
			return null;
		}
		for (int i = 0; i < src.length; i++) {
			int v = src[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv).append("-");
		}
		return stringBuilder.toString();
	}

	// 线程:监听服务器发来的消息
	private Runnable mRunnable = new Runnable() {
		public void run() {
			String msgText = IPText.getText().toString();
			if (msgText.length() <= 0) {
				// Toast.makeText(mContext, "IP不能为空！",
				// Toast.LENGTH_SHORT).show();
				recvMessageClient = "IP不能为空！\n";// 消息换行
				Message msg = new Message();
				mHandler.sendMessage(msg);
				return;
			}
			int start = msgText.indexOf(":");
			if ((start == -1) || (start + 1 >= msgText.length())) {
				recvMessageClient = "IP地址不合法\n";// 消息换行
				Message msg = new Message();
				mHandler.sendMessage(msg);
				return;
			}
			String sIP = msgText.substring(0, start);
			String sPort = msgText.substring(start + 1);
			int port = Integer.parseInt(sPort);

			Log.d("gjz", "IP:" + sIP + ":" + port);

			try {
				// 连接服务器
				mSocketClient = new Socket(sIP, port); // portnum
				// 取得输入、输出流
				mBufferedReaderClient = new BufferedReader(
						new InputStreamReader(mSocketClient.getInputStream()));

				mPrintWriterClient = new PrintWriter(
						mSocketClient.getOutputStream(), true);

				recvMessageClient = "已经连接server!\n";// 消息换行
				Message msg = new Message();
				mHandler.sendMessage(msg);
				// break;
			} catch (Exception e) {
				recvMessageClient = "连接IP异常:" + e.toString() + e.getMessage()
						+ "\n";// 消息换行
				Message msg = new Message();
				mHandler.sendMessage(msg);
				return;
			}

			byte[] buffer = new byte[4096000];
			int count = 0;
			while (isConnecting) {
				try {
					// if ( (recvMessageClient =
					// mBufferedReaderClient.readLine()) != null )

					InputStream in = mSocketClient.getInputStream();
					count = in.read(buffer);

					if (count > 10) {
						// recvMessageClient=bytesToHexString(buffer);

						recvMessageClient = getInfoBuff(buffer, count) + "\n";// 消息换行
						f=getFloat(buffer, count);
						Message msg = new Message();
						mHandler.sendMessage(msg);
					}
				} catch (Exception e) {
					recvMessageClient = "接收异常:" + e.getMessage() + "\n";// 消息换行
					Message msg = new Message();
					mHandler.sendMessage(msg);
				}
			}
		}
	};

	Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);

			recvText.append("Client: " + recvMessageClient); // 刷新

		}

	};
	
	public float[] getFloat(byte[] buff, int count){
		int sum = 0;
		int cmd=0;
		float []f=new float[20000];
		cmd = (int) BitConverter.ByteToLong(buff, 44);

		if(cmd == -1879048191) {
			sum = (int) BitConverter.ByteToLong(buff, 52);
			for (int i = 0; i < sum; i++) {
				float xxx = (float) BitConverter.ByteToInt(buff, 56 + 2 * i);
				f[i]=xxx;
			}
		} 
		return f;
	}
	private String getInfoBuff(byte[] buff, int count) {
		StringBuilder rec = new StringBuilder();
		StringBuilder fanhui = new StringBuilder();
		int sum = 0;
		int cmd=0;
		
		cmd = (int) BitConverter.ByteToLong(buff, 44);
		//System.console();
		System.out.print(cmd);
		if (cmd == -1610612736) {
			fanhui.append("测量中。。。");
		} 
		else if(cmd == -1879048191) {
			sum = (int) BitConverter.ByteToLong(buff, 52);
			for (int i = 0; i < sum; i++) {
				float xxx = (float) BitConverter.ByteToInt(buff, 56 + 2 * i);
				rec.append((xxx / 1000 - 5) + "\n");
			}

			fanhui = rec;
		} 
		else {
			fanhui.append("测量失败！正在重试。。。");
		}
		return new String(fanhui);
	}
}