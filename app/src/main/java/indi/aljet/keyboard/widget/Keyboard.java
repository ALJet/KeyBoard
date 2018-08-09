package indi.aljet.keyboard.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.util.AttributeSet;
import android.widget.TextView;

import indi.aljet.keyboard.R;


public class Keyboard extends RelativeLayout {

    private Context context;
    private GridView gvKeyboard;

    private String[] key;
    private OnClickKeyboardListener onClickKeyboardListener;


    public Keyboard(Context context) {
        this(context, null);
    }

    public Keyboard(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Keyboard(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }


    //初始化键盘的点击事件
    private void initEvent(){
        gvKeyboard.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(onClickKeyboardListener != null &&
                        i >= 0){
                    onClickKeyboardListener.onKeyClick(i,key[i]);
                }
            }
        });
    }

    //初始化keyboardView
    private void initKeyboardView(){
        View view = View.inflate(context, R.layout
        .view_keyboard,this);
        gvKeyboard = (GridView)view.findViewById(R.id
        .gv_keyboard);
        gvKeyboard.setAdapter(keyboardAdapter);
        initEvent();
    }

    public interface OnClickKeyboardListener{
        void onKeyClick(int position,String value);
    }

    public void setOnClickKeyboardListener(OnClickKeyboardListener onClickKeyboardListener){
        this.onClickKeyboardListener = onClickKeyboardListener;
    }


    //设置键盘显示的内容
    public void setKeyboardKeys(String[] key){
        this.key = key;
        initKeyboardView();
    }

    private BaseAdapter keyboardAdapter = new BaseAdapter() {

        private static final int KEY_NINE = 9;

        @Override
        public int getCount() {
            return key.length;
        }

        @Override
        public Object getItem(int i) {
            return key[i];
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public int getViewTypeCount() {
            return 2;
        }

        @Override
        public int getItemViewType(int position) {
            return (getItemId(position) == KEY_NINE)
                    ? 2 : 1;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder viewHolder = null;
            if(view == null){
                if(getItemViewType(i) == 1){
                    //数字键
                    view = LayoutInflater.from(context)
                            .inflate(R.layout.item_grid_keyboard,
                                    viewGroup,false);
                    viewHolder = new ViewHolder(view);
                }else{
                    //删除键
                    view = LayoutInflater.from(context)
                            .inflate(R.layout.item_grid_keyboard_delete,
                                    viewGroup,false);
                }
            }
            if(getItemViewType(i) == 1){
                viewHolder = (ViewHolder) view
                        .getTag();
                viewHolder.tvkey.setText(key[i]);
            }
            return view;
        }
    };

    static class ViewHolder{
        private TextView tvkey;

        public ViewHolder(View view) {
            tvkey = view.findViewById(R.id.tv_keyboard_keys);
            view.setTag(this);
        }
    }


}
