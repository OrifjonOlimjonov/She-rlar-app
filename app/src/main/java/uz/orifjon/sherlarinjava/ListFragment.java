package uz.orifjon.sherlarinjava;

import static android.content.Context.CLIPBOARD_SERVICE;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import uz.orifjon.sherlarinjava.adapter.RecyclerViewAdapter;
import uz.orifjon.sherlarinjava.databinding.CustomdialogBinding;
import uz.orifjon.sherlarinjava.databinding.FragmentListBinding;
import uz.orifjon.sherlarinjava.models.Poems;
import uz.orifjon.sherlarinjava.singleton.MyGson;
import uz.orifjon.sherlarinjava.singleton.MySharedPreference;

public class ListFragment extends Fragment {

    public ListFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    private FragmentListBinding binding;
    private Map<String, List<Poems>> map;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private List<Poems> likeList;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentListBinding.inflate(inflater, container, false);
        Bundle arguments = getArguments();

        String s = MySharedPreference.getInstance(requireContext()).get();
        Type type = new TypeToken<Map<String, List<Poems>>>() {
        }.getType();
        map = MyGson.getInstance().getGson().fromJson(s, type);
        sharedPreferences = requireContext().getSharedPreferences("likes", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        String name = arguments.getString("name");
        binding.toolbar.setTitle(name);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(map.get(name), poem -> {
            List<Poems> list = map.get(name);
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(requireContext());
            CustomdialogBinding binding;
            binding = CustomdialogBinding.inflate(getLayoutInflater());
            alertDialog.setView(binding.getRoot());
            if (poem.isLike()) binding.likeImg.setImageResource(R.drawable.like_true);
            binding.titleNamePoem.setText(poem.getName());
            binding.poemDialog.setText(poem.getText());
            binding.message.setOnClickListener(view -> {
                Intent myIntent = new Intent(Intent.ACTION_SEND);
                String body = poem.getName();
                String sub = poem.getText();
                myIntent.setType("vnd.android-dir/mms-sms/");
                myIntent.putExtra(Intent.EXTRA_SUBJECT, sub);
                myIntent.putExtra(Intent.EXTRA_TEXT, body);
                startActivity(Intent.createChooser(myIntent, "Share Using"));
                Toast.makeText(requireContext(), "Message", Toast.LENGTH_SHORT).show();
            });
            binding.like.setOnClickListener(view -> {
                String like = sharedPreferences.getString("like", "");
                if (like.isEmpty()) {
                    likeList = new ArrayList<>();
                    likeList.add(poem);
                } else {
                    boolean isObsent = false;
                    int index = -1;
                    Type type1 = new TypeToken<List<Poems>>() {
                    }.getType();
                    likeList = MyGson.getInstance().getGson().fromJson(like, type1);
                    for (int i = 0; i < likeList.size(); i++) {
                        if (likeList.get(i).getName().equals(poem.getName())) {
                            isObsent = true;
                            index = i;
                        }
                    }
                    if (isObsent) {
                        Toast.makeText(requireContext(), "Dislike", Toast.LENGTH_SHORT).show();
                        likeList.remove(index);
                        index = -1;
                        for (int i = 0; i < list.size(); i++) {
                            if(list.get(i).equals(poem.getName())){
                                index = i;
                            }
                        }
                        if(index != -1){
                            list.get(index).setLike(false);
                            map.put(name,list);
                            String s1 = MyGson.getInstance().getGson().toJson(map);
                            MySharedPreference.getInstance(requireContext()).set(s1);
                        }
                        binding.likeImg.setImageResource(R.drawable.ic_like_icon);
                    } else {
                        Toast.makeText(requireContext(), "Like", Toast.LENGTH_SHORT).show();
                        likeList.add(poem);
                        for (int i = 0; i < list.size(); i++) {
                            if(list.get(i).equals(poem.getName())){
                                index = i;
                            }
                        }
                        if(index != -1){
                            list.get(index).setLike(true);
                            map.put(name,list);
                            String s1 = MyGson.getInstance().getGson().toJson(map);
                            MySharedPreference.getInstance(requireContext()).set(s1);
                        }
                        binding.likeImg.setImageResource(R.drawable.like_true);
                    }
                }
                String s1 = MyGson.getInstance().getGson().toJson(likeList);
                editor.putString("like", s1).commit();
            });

            binding.share.setOnClickListener(view -> {
                Intent myIntent = new Intent(Intent.ACTION_SEND);
                String body = poem.getName();
                String sub = poem.getText();
                myIntent.setType("text/plain");
//                myIntent.setType("image/jpeg");
//                myIntent.setType("audio/mpeg4-generic");
//                myIntent.setType("text/html");
//                myIntent.setType("audio/mpeg");
//                myIntent.setType("audio/aac");
//                myIntent.setType("audio/wav");
//                myIntent.setType("audio/ogg");
//                myIntent.setType("audio/midi");
//                myIntent.setType("audio/x-ms-wma");
//                myIntent.setType("video/mp4");
//                myIntent.setType("video/x-msvideo");
//                myIntent.setType("video/x-ms-wmv");
//                myIntent.setType("image/png");
//                myIntent.setType("image/jpeg");
//                myIntent.setType("image/gif");
//                myIntent.setType("text/xml");
//                myIntent.setType("text/plain");
//                myIntent.setType(".cfg -> text/plain");
//                myIntent.setType(".csv -> text/plain");
//                myIntent.setType(".conf -> text/plain");
//                myIntent.setType(".rc -> text/plain");
//                myIntent.setType(".htm -> text/html");
//                myIntent.setType(".html -> text/html");
//                myIntent.setType(".pdf -> application/pdf");
                myIntent.putExtra(Intent.EXTRA_SUBJECT, sub);
                myIntent.putExtra(Intent.EXTRA_TEXT, body);
                startActivity(Intent.createChooser(myIntent, "Share Using"));
                Toast.makeText(requireContext(), "Message", Toast.LENGTH_SHORT).show();
                Toast.makeText(requireContext(), "Share", Toast.LENGTH_SHORT).show();
            });
            binding.copy.setOnClickListener(view -> {
                ClipboardManager clipboard = (ClipboardManager) getActivity().getSystemService(CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("label", binding.poemDialog.getText().toString());
                clipboard.setPrimaryClip(clip);
                Toast.makeText(requireContext(), "Message copied to clipboard ", Toast.LENGTH_SHORT).show();
            });
            AlertDialog alertDialog1 = alertDialog.create();
            Window window = alertDialog1.getWindow();
            WindowManager.LayoutParams wlp = window.getAttributes();
            wlp.gravity = Gravity.BOTTOM;
            wlp.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;
            window.setAttributes(wlp);
            alertDialog1.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            alertDialog1.show();
        });

        binding.rv.setAdapter(adapter);

        binding.btnBack.setOnClickListener(view -> {
            Navigation.findNavController(binding.getRoot()).popBackStack();
        });
        return binding.getRoot();
    }
}