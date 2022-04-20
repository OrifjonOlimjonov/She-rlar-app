package uz.orifjon.sherlarinjava;

import static android.content.Context.CLIPBOARD_SERVICE;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
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
import java.util.List;

import uz.orifjon.sherlarinjava.adapter.RecyclerViewAdapter;
import uz.orifjon.sherlarinjava.adapter.RecyclerViewLikesAdapter;
import uz.orifjon.sherlarinjava.databinding.CustomdialogBinding;
import uz.orifjon.sherlarinjava.databinding.FragmentSaveBinding;
import uz.orifjon.sherlarinjava.models.Poems;
import uz.orifjon.sherlarinjava.singleton.MyGson;


public class SaveFragment extends Fragment {

    public SaveFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("TAG", "onCreate: Save fragment");

    }

    private FragmentSaveBinding binding;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private List<Poems> list;
    private RecyclerViewLikesAdapter adapter = null;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSaveBinding.inflate(inflater, container, false);
        list = new ArrayList<>();
        sharedPreferences = requireContext().getSharedPreferences("likes", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        String like = sharedPreferences.getString("like", "");
        if(like.isEmpty()){
            Toast.makeText(requireContext(),"Saqlanganlar mavjud emas!!",Toast.LENGTH_SHORT).show();
        }else {
            Type type = new TypeToken<List<Poems>>() {
            }.getType();
            list = MyGson.getInstance().getGson().fromJson(like, type);
              adapter = new RecyclerViewLikesAdapter(list, poem -> {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(requireContext());
                CustomdialogBinding binding;
                binding = CustomdialogBinding.inflate(getLayoutInflater());
                alertDialog.setView(binding.getRoot());
                binding.titleNamePoem.setText(poem.getName());
                binding.poemDialog.setText(poem.getText());

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

                binding.like.setOnClickListener(view -> {
                        list.remove(poem);
                        adapter.change(list);
                    String s = MyGson.getInstance().getGson().toJson(list);
                    editor.putString("like",s).commit();
                });
            });
            binding.rv.setAdapter(adapter);
        }

        binding.btnBack.setOnClickListener(view -> {
            Navigation.findNavController(binding.getRoot()).popBackStack();
        });


        Log.d("TAG", "onCreateView: Save fragment");
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("TAG", "onResume: Save fragment");
    }
}