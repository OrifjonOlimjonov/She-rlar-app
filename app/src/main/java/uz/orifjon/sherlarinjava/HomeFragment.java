package uz.orifjon.sherlarinjava;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import uz.orifjon.sherlarinjava.databinding.FragmentHomeBinding;
import uz.orifjon.sherlarinjava.models.Poems;
import uz.orifjon.sherlarinjava.singleton.MyGson;
import uz.orifjon.sherlarinjava.singleton.MySharedPreference;

public class HomeFragment extends Fragment {


    public HomeFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private Map<String, List<Poems>> map;
    private FragmentHomeBinding binding;
    private List<Poems> list;
    private SharedPreferences sharedPreferences;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        loadData();
        String s = MyGson.getInstance().getGson().toJson(map);
        MySharedPreference.getInstance(requireContext()).set(s);
        sharedPreferences = requireContext().getSharedPreferences("likes", Context.MODE_PRIVATE);
        String like = sharedPreferences.getString("like", "");
        if(like.isEmpty()){
            binding.savedPoem.setText("0 ta");
        }else {
            Type type = new TypeToken<List<Poems>>() {
            }.getType();
            list = MyGson.getInstance().getGson().fromJson(like, type);
            binding.savedPoem.setText(list.size() + " ta");
        }
        binding.news.setOnClickListener(view -> {
            Navigation.findNavController(binding.getRoot()).navigate(R.id.newsFragment);
        });

       binding.likes.setOnClickListener(view -> {
           Navigation.findNavController(binding.getRoot()).navigate(R.id.saveFragment);
       });
        binding.btnCd1.setOnClickListener(view -> {
            Bundle bundle = new Bundle();
            bundle.putString("name", "Sevgi she'rlari");
            Navigation.findNavController(binding.getRoot()).navigate(R.id.listFragment, bundle);
        });
        binding.btnCd2.setOnClickListener(view -> {
            Bundle bundle = new Bundle();
            bundle.putString("name", "Ibrat");
            Navigation.findNavController(binding.getRoot()).navigate(R.id.listFragment, bundle);
        });
        binding.btnCd3.setOnClickListener(view -> {
            Bundle bundle = new Bundle();
            bundle.putString("name", "Tabrik she'rlari");
            Navigation.findNavController(binding.getRoot()).navigate(R.id.listFragment, bundle);
        });
        binding.btnCd4.setOnClickListener(view -> {
            Bundle bundle = new Bundle();
            bundle.putString("name", "Ota-ona xaqida");
            Navigation.findNavController(binding.getRoot()).navigate(R.id.listFragment, bundle);
        });
        binding.btnCd5.setOnClickListener(view -> {
            Bundle bundle = new Bundle();
            bundle.putString("name", "Do'stlik haqida");
            Navigation.findNavController(binding.getRoot()).navigate(R.id.listFragment, bundle);
        });
        binding.btnCd6.setOnClickListener(view -> {
            Bundle bundle = new Bundle();
            bundle.putString("name", "Islomiy she'rlar");
            Navigation.findNavController(binding.getRoot()).navigate(R.id.listFragment, bundle);
        });

        return binding.getRoot();
    }

    private void loadData() {
        map = new HashMap<>();
        list = new ArrayList<>();

        list.add(new Poems("Xavotir", "Qoʻrqaman, ertaga men oʻlib ketsam,\nYotar boʻlsam qumga botib koʻzlarim,\nKoʻnglimni koʻchkiday bosadi bir gʻam \nYigʻlashni ham bilmas mening qizlarim…"));
        list.add(new Poems("Sodda Muhammadman", "Sodda Muhammadman, Sodda Muhammad.\n" +
                "\n" +
                "Turkman qizni maqtab baloga qoldim.\n" +
                "\n" +
                "Koʻylagiga havas qilgandim faqat,\n" +
                "\n" +
                "Toabad tuganmas gʻavgʻoga qoldim…\n" +
                "\n" +
                " \n" +
                "\n" +
                "Nedan ranjidingiz, jon singillarim,\n" +
                "\n" +
                "Qaboqlar uyuldi, chimrildi qoshlar.\n" +
                "\n" +
                "Turkman tushunmay tosh otsa, chidadim,\n" +
                "\n" +
                "Ammo yomon botdi siz otgan toshlar."));

        list.add(new Poems("Qushlar ham yigʻlar", "Terlar oqdi suv boʻlib bizdan\n" +
                "\n" +
                "Va gursillab yiqildi terak…\n" +
                "\n" +
                "Aka, bizning qilmishimizdan\n" +
                "\n" +
                "Tunda qushlar yigʻlasa kerak.\n" +
                "\n" +
                " \n" +
                "\n" +
                "Ona chumchuq aylanib ketmas,\n" +
                "\n" +
                "Koʻngliga qil sigʻmasa kerak.\n" +
                "\n" +
                "Eski indan tashib borib xas,\n" +
                "\n" +
                "Yangi inda yigʻlasa kerak.\n" +
                "\n" +
                " \n" +
                "\n" +
                "Baland boʻlar uyimiz togʻdek,\n" +
                "\n" +
                "Ayvonida ustun ul terak.\n" +
                "\n" +
                "Biz yasharmiz, koʻnglimiz chogʻdek,\n" +
                "\n" +
                "Shunda qushlar yigʻlasa kerak…"));
        list.add(new Poems("To'rtlik", "Ey ko'z, ko'r bo'lmasang go'rdan bo'l ogoh,\n" +
                "Fitna to'la olam, har yon qayg'u-oh.\n" +
                "Chumoli og'zida guldek yonoqlar,\n" +
                "Yer qo'ynida sulton, sardor, podshoh...."));
        list.add(new Poems("To'rtlik", "O'z erkingda emas, axir, erta kun.\n" +
                "Ertangni ko'p o'ylab bo'lma jigarxun.\n" +
                "Bu damni bekorga ketkazma aslo,\n" +
                "Bilib qo'y, dunyoga bo'lmaysan ustun."));
        list.add(new Poems("Ayol", "Siz ayolni arra demang,\n" +
                "Bir oz qahrga toʼlsa.\n" +
                "U arraga aylanajak,\n" +
                "Yonida toʼnka boʼlsa!"));
        map.put("Sevgi she'rlari", list);
        list = new ArrayList<>();
        list.add(new Poems("Tushunmas", "Chumchuq anglar, chumchuq chugʼurlasa gar,\n" +
                "Qashqir uvlaganda uvlar qashqir ham.\n" +
                "Hatto zar tilini biladi zargar,\n" +
                "Аyt, nega tushunmas odamni odam?!"));
        list.add(new Poems("Kalit", "Soʼz – kalitdir, agar sen,\n" +
                "Uni toʼgʼri topolsang –\n" +
                "Har qanday dilni ochib,\n" +
                "Har ogʼizni yoparsan."));
        list.add(new Poems("Zirak", "Ziragin yoʼqotdi, dod soldi chunon,\n" +
                "Bilmadi tong otib, kun botganini\n" +
                "Va, afsus, sezmadi u allaqachon\n" +
                "Boylik deb oʼzligin yoʼqotganini."));
        list.add(new Poems("Xasad", "Hech kimga soʼzlama siringni, hay-hay!\n" +
                "Hasad olovida qovurilgan dam –\n" +
                "Basir koʼra boshlar, soqov sayragay,\n" +
                "Eshita boshlaydi hatto garang ham!"));
        list.add(new Poems("Kimsan?", "Seni kim deb atay, garchi qadding gʼoz,\n" +
                "Kesib oʼtolmaydi hech kim yoʼlingni.\n" +
                "Yiqilgan odamga bedard, begʼaraz\n" +
                "Bir bora choʼzmagan boʼlsang qoʼlingni?!"));
        list.add(new Poems("Yo'qotma", "Eshakka ishonib toyni yoʼqotma,\n" +
                "Dudga koʼngil qoʼyib nayni yoʼqotma.\n" +
                "Qadringga yetganni qadrdon bilgin,\n" +
                "Yulduz sanayman deb oyni yoʼqotma."));
        list.add(new Poems("Dono va Nodon", "Donish oʼzin asrar, nodon singari\n" +
                "Qoʼltigʼida kovushini koʼtarmas.\n" +
                "Nokas qichqiradi eldan ilgari,\n" +
                "Аqlli zot tovushini koʼtarmas."));
        list.add(new Poems("Bugun", "Sen Bugunni qadrla, oshna,\n" +
                "Bolangga ham shu gapni uqtir!\n" +
                "Topolmaysan Kechani aslo,\n" +
                "Erta esa kelgani yoʼqdir."));
        list.add(new Poems("Taqvo", "Hazrati Umardan soʼradi bir kun:\n" +
                "«Ustoz, taqvo nima? U qanday ishdir?»\n" +
                "Hazrat der: «Sertikon katta maydondan\n" +
                "Oyoqyalang omon oʼtib olishdir»."));
        list.add(new Poems("Pand", "Oqar daryoga ham kimdir band bergan,\n" +
                "Hayot kimga zahar, kimga qand bergan.\n" +
                "Ey, g‘ofil, sarkash deb, meni kamsitma,\n" +
                "G’anim senga emas, menga pand bergan."));
        map.put("Ibrat", list);

        list = new ArrayList<>();
        list.add(new Poems("Tavallud ayyom", "Sen uchun muborak tavalllud ayyom\n" +
                "\n" +
                "Bugun o'ynab kulgin sen uchun bayram.\n" +
                "\n" +
                "Bayramiy kayfiyat hamrohing bo'lsin\n" +
                "\n" +
                "Dunyoda eng buyuk Alloh ham suysin.\n"));
        list.add(new Poems("Ona", "Doim kulib yuring tabassum bilan.\n" +
                "Yashang doim baxtga oshyona.\n" +
                "Mexrdan poyizga to'shayman gilam.\n" +
                "Tug'ulgan kuningiz bilan ona. "));
        list.add(new Poems("Opa", "Soxtalik ko'paydi, tabiydan bugun.\n" +
                "Siz bilan butundir gar dunyo birkam!\n" +
                "Eng sodiq do'stimsiz ukangiz uchun.\n" +
                "Tug'ilgan kun ingiz bilan opam. "));
        list.add(new Poems("Dada", "Siz mening savlatim, siz mening faxrim.\n" +
                "Ishonchli qalqonim, xayotga dalda.\n" +
                "Xayotda takrorsiz, eng oliy baxtim.\n" +
                "Tug'ilgan kuningiz bilan dada."));
        list.add(new Poems("O'g'lim", "Bazida sendan jaxillanaman.\n" +
                "Lekin xar laxzada keladi ko'rgim.\n" +
                "Ishon sendan faxrlanaman.\n" +
                "Tug'ilgan kuning bilan o'g'lim. "));
        list.add(new Poems("Aka yoki uka", "Sizdan ko'rkam yigit yo'q bu kun.\n" +
                "Doim mag'rur, g'ururi butun.\n" +
                "Qo'shilib barcha ezgu tilakka.\n" +
                "Tug'ulgan kuningiz bilan aka. (uka) "));
        list.add(new Poems("Singlim", "Senday dardkash, yo'qdir mehribon.\n" +
                "Xarqanday xolatda saqlagan sirim.\n" +
                "Seni yaxshi ko'raman ishon.\n" +
                "Tug'ulgan kuning bilan singlim."));
        list.add(new Poems("Yaqin insonga", "Tilaklar ichida izlab topganim,\n" +
                "Alloh nigohini sizga qaratsin.\n" +
                "Yagona o’tinchim Allohdan bu kun,\n" +
                "Sizdek insonlarni ko’proq yaratsin!\n" +
                "Tavallud ayyomingiz qutlug’ bo’lsin!"));
        list.add(new Poems("To'y tabrigi", "Nurlarga ko'milgan bu maskan bu kun,\n" +
                "Sizlarga baxt tilar pok niyat ila.\n" +
                "Oppoq libosdagi ko'ngli oq kelin,\n" +
                "Baxtli bo'ling bu kun yoringiz birla."));
        list.add(new Poems("Tabrik", "Orzuli dunyoda orzular qilib,\n" +
                "Sarob-u armonni bir chetga surib,\n" +
                "Yuragingiz yayrab, labingiz kulib,\n" +
                "Yashang bu dunyoda eng baxtli bo’lib. "));
        map.put("Tabrik she'rlari", list);

        list = new ArrayList<>();
        list.add(new Poems("Yetar endi", "Hayot tashvishlarin ko‘rgan ko‘pini,\n" +
                "Toqat qilgan, yig‘lab tolmagan.\n" +
                "Chekkan zaxmatlarning bori, barini,\n" +
                "Kerak bo‘lsa orim olmagan .\n" +
                "Mening opshal ulug‘ onamni,\n" +
                "Yetar endi yig‘latma hayot. "));
        list.add(new Poems("Onam", "Toʼqqiz oy vujudim vujudingizda.\n" +
                "Koʼtarib yurdingiz bir parcha tanam.\n" +
                "Ilk bora yigʼladim qoʼllaringizda.\n" +
                "Quvonib koʼzga yosh oldingiz onam.\n" +
                "\n" +
                "Jajji qoʼllarimni koʼzingiz surib.\n" +
                "Yoshingiz artingiz deya jon bolam.\n" +
                "Yuzimga boqdingiz jilmayib kulib.\n" +
                "Аlloxga shukurlar aytkansiz onam."));
        list.add(new Poems("Onam uchun", "Koz yoshlari dary bolib oqqanida\n" +
                "Senga gozal kozi bilan boqqanida\n" +
                "Boor mehrini sening uchun sochganida\n" +
                "Nima qilding dostim bugun onang uchun "));
        list.add(new Poems("Otang rozi", "Omad senga qo’lllarini tutaverar,\n" +
                "Yaxshi kunlar yo’llaringni kutaverar,\n" +
                "O’taverar hayot go’zal o’taverar,\n" +
                "Onajoning rozi bo’lsa agar sendan,\n" +
                "Otajoning rozi bo’lsa agar sendan.\n"));
        list.add(new Poems("Dada", "Sodda, begʼuborligim, \n" +
                "Koʼngli oppoq qorligim, \n" +
                "Аrmonlari borligim, \n" +
                "Dada, sizga oʼxshayman."));
        list.add(new Poems("Ona", "Jonimga jon bergan sadarayhonim, \n" +
                "Koʼzimga nur bergan nuri jahonim, \n" +
                "Bugun yuragimga sigʼmaydi ohim, \n" +
                "Endi esim kirdi, boʼlay parvona, \n" +
                "Faqat qarimasdan chidab ber, ona."));
        list.add(new Poems("Ota", "O'zimcha haq bo'lib ilmadim so'zing\n" +
                "O'g'il bo'lib nima qildim men ota\n" +
                "Oqlolmadim o'sha bergan non tuzing\n" +
                "Mendan yaxshi farzand chiqmadi ota"));
        list.add(new Poems("Duosini ol onangni", "Yashaging kelar har on\n" +
                "Dilni qilib charog'on\n" +
                "Bo'lar yo'ling farovon\n" +
                "Duosin ol onangni "));
        map.put("Ota-ona xaqida", list);

        list = new ArrayList<>();

        list.add(new Poems("Chin do'st", "Og'ir kunda tirgak do'st,\n" +
                "Havotirda sergak do'st,\n" +
                "100 ta dushman oldida,\n" +
                "Qochmas aslo erkak do'st!\n" +
                "\n" +
                "Dushmanlari ortganda,\n" +
                "Sohta do'stlar qochganda,\n" +
                "Barcha qo'lin tortganda,\n" +
                "Qo'lin cho'zar Chindan do'st! "));
        list.add(new Poems("Do'st", "Siz ishoning burda noningiz,\n" +
                "Menga aslo –aslo kerakmas.\n" +
                "Siz yurgan yoʼl butunlay boshqa,\n" +
                "Sizning yoʼlak bizning yoʼlakmas."));
        list.add(new Poems("Ne qayg'urmay...", "Dushmanlari koʼp boʼlsa,\n" +
                " Meva daraxti soʼlsa,\n" +
                "Ichi qaygʼuga toʼlsa,\n" +
                " Ne qaygʼurmay, doʼstimga…\n" +
                "\n" +
                "Zaxrin sochganda ilon,\n" +
                " Qamrasa shubxa gumon,\n" +
                "Аxvoli tushkun yomon,\n" +
                " Ne qaygʼurmay, doʼstimga…"));

        list.add(new Poems("Do'st", "Men qandayin do'st bo'ldim chin do'stim uchun,\n" +
                "og'ir paytda do'stimni tashlab ketdim-a,\n" +
                "o'ylamadim ahvoli ne kecharkan deb,\n" +
                "shunchalar nomard do'st ekanman bildim.\n"));

        list.add(new Poems("Do'st", "Tilayman ey doʼstim, har qanday damda,\n" +
                "Nomardu-nokasga ishing tushmasin.\n" +
                "Gʼanimlar oldida qolsang alamda,\n" +
                "Koʼzingdan bir tomchi yoshing tushmasin."));
        list.add(new Poems("Unutma!", "Ey, do‘st, unut g‘amingni,\n" +
                "In’om bil har damingni.\n" +
                "O‘lchab bos qadamingni,\n" +
                "Guvohingni unutma!\n" +
                "Ey, do‘st, qo‘rqma yomondan,\n" +
                "Yo hiylagar shaytondan,\n" +
                "Shart shuki, har zamonda\n" +
                "Panohingni unutma! "));
        list.add(new Poems("Do'st", "Bir tomchi suvdan favvora bo'lmas, \n" +
                "Do'sti bor inson hech qachon bechora bo'lmas. \n" +
                "Ishongin so'zlarim, rostdir hammasi, \n" +
                "Dunyoda hech bir do'st sendek bo'lolmas."));
        map.put("Do'stlik haqida", list);

        list = new ArrayList<>();
        list.add(new Poems("Ramazon", "Qalbni nurga to`ldirgan Iymon oyi muborak!\n" +
                "Jannat yo`lin ko`rsatgan Qur`on oyi muborak.\n" +
                "Ko`kdan tushgan malaklar - fayizli oylar muborak.\n" +
                "Ro`zadorga Rаmozon oyi muborak! "));
        list.add(new Poems("Xush kelibsan!", "Shuʼlalansin koʼnglimiz bu kun,\n" +
                "Аvjlansin misli toshqin sel. \n" +
                "Diydoringga yuragim maftun, \n" +
                "Dil yoritar – nur – azonim, kel,\n" +
                "Mushtoq kutgan – Ramazonim, kel. \n" +
                "\n" +
                "Ezgulikni dillarda elab,\n" +
                "Yod tuygʼuni uchirsin nur – yel. \n" +
                "Qalbni iymon nuriga belab,\n" +
                "Dil yoritar – nur – azonim, kel,\n" +
                "Mushtoq kutgan – Ramazonim kel."));
        list.add(new Poems("Xayit muborak", "Iymonning oxiri nurli kelajak\n" +
                "Jannatga kalit yo'q namozdan bo'lak.\n" +
                "Maqsad yo'q savob ish qilishdan bo'lak,\n" +
                "Аllohim sevgan kun -xayit muborak!"));
        list.add(new Poems("Kim tanisa ?", "Farqlash zarur har bir kishiga,\n Savob ila zarar gunohni,\n Puxta erur biling ishiga\n kim tanisa Rasulullohni!!"));
        list.add(new Poems("Bizlarni kechiring!", "Ko'rmayin ergashdik izingiz haqqi\n Jannatda porlagan yuzingiz haqqi.\nUmmatim! dedingiz so'zingiz haqqi,\nBizlarni kechiring yo Rosululloh!\n" +
                "\nSigaret chekyapmiz musulmonmiz deb,\n Aroq ham ichyapmiz musulmonmiz deb\nFaxshni quchyapmiz musulmonmiz deb\nBizlarni kechiring yo Rosululloh!\n" +
                "\nTiniq buloq suvin voh loyqalatib\n Eh, bizlar dunyo deb hech to'ymayapmiz.\nOchko'z bo'lib qoldik yeb to'ymayapmiz\nBizlarni kechiring yo Rosululloh!"));

        map.put("Islomiy she'rlar", list);
    }
}