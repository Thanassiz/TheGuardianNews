package com.example.android.theguardiannews;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSpinner;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.android.theguardiannews.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private final String TAG = MainActivity.class.getSimpleName();

    private ActivityMainBinding binding;
    AppCompatSpinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        // Attaching the layout to the toolbar object
        // Setting toolbar as the ActionBar with setSupportActionBar() call
        setSupportActionBar(binding.includeToolbar.toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        // Setup Navigation drawer
        setupDrawerItem(binding.navView);

        binding.includeToolbar.drawerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.drawer.openDrawer(Gravity.LEFT);
            }
        });

        CategoryAdapter categoryAdapter = new CategoryAdapter(this, getSupportFragmentManager());
        binding.viewpager.setAdapter(categoryAdapter);
        setSpinner();
        binding.viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                Log.e(TAG, "VIEWPAGER ->" + position);
                // Set spinner similar to the corresponding viewpager's fragment position
                spinner.setSelection(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private void setupDrawerItem(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                selectDrawerItem(menuItem);
                return true;
            }
        });
    }

    private void selectDrawerItem(MenuItem menuItem) {

        switch (menuItem.getItemId()) {
            case R.id.nav_settings:
                Toast.makeText(getApplicationContext(), "This feature will be added in the next patch!", Toast.LENGTH_SHORT).show();
                // Close the navigation drawer
                binding.drawer.closeDrawers();
                break;
            case R.id.nav_spinner:
                Toast.makeText(getApplicationContext(), "Select news from the list.", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

    /**
     * Whenever spinner News is selected it sends you to the corresponding News fragment
     */
    private void setSpinner() {

        final ArrayList<String> spinnerList = new ArrayList<>();
        spinnerList.add(getString(R.string.fragment_home));
        spinnerList.add(getString(R.string.fragment_music));
        spinnerList.add(getString(R.string.fragment_business));
        spinnerList.add(getString(R.string.fragment_technology));
        spinnerList.add(getString(R.string.fragment_politics));
        spinnerList.add(getString(R.string.fragment_sport) + "s");
        spinnerList.add(getString(R.string.fragment_weather));
        spinnerList.add(getString(R.string.fragment_film) + "s");
        spinnerList.add(getString(R.string.fragment_money));
        spinnerList.add(getString(R.string.fragment_education));
        spinnerList.add(getString(R.string.fragment_environment));
        spinnerList.add(getString(R.string.fragment_fashion));

        spinner = (AppCompatSpinner) binding.navView.getMenu().findItem(R.id.nav_spinner).getActionView();
        spinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, spinnerList));

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e(TAG, "SPINNER ->" + position);
                Toast.makeText(MainActivity.this, spinnerList.get(position), Toast.LENGTH_SHORT).show();
                switch (position) {
                    case Constants.HOME:
                        binding.viewpager.setCurrentItem(Constants.HOME);
                        binding.drawer.closeDrawers();
                        break;
                    case Constants.MUSIC:
                        binding.viewpager.setCurrentItem(Constants.MUSIC);
                        binding.drawer.closeDrawers();
                        break;
                    case Constants.BUSINESS:
                        binding.viewpager.setCurrentItem(Constants.BUSINESS);
                        binding.drawer.closeDrawers();
                        break;
                    case Constants.TECHNOLOGY:
                        binding.viewpager.setCurrentItem(Constants.TECHNOLOGY);
                        binding.drawer.closeDrawers();
                        break;
                    case Constants.POLITICS:
                        binding.viewpager.setCurrentItem(Constants.POLITICS);
                        binding.drawer.closeDrawers();
                        break;
                    case Constants.SPORTS:
                        binding.viewpager.setCurrentItem(Constants.SPORTS);
                        binding.drawer.closeDrawers();
                        break;
                    case Constants.WEATHER:
                        binding.viewpager.setCurrentItem(Constants.WEATHER);
                        binding.drawer.closeDrawers();
                        break;
                    case Constants.FILM:
                        binding.viewpager.setCurrentItem(Constants.FILM);
                        binding.drawer.closeDrawers();
                        break;
                    case Constants.MONEY:
                        binding.viewpager.setCurrentItem(Constants.MONEY);
                        binding.drawer.closeDrawers();
                        break;
                    case Constants.EDUCATION:
                        binding.viewpager.setCurrentItem(Constants.EDUCATION);
                        binding.drawer.closeDrawers();
                        break;
                    case Constants.ENVIRONMENT:
                        binding.viewpager.setCurrentItem(Constants.ENVIRONMENT);
                        binding.drawer.closeDrawers();
                        break;
                    case Constants.FASHION:
                        binding.viewpager.setCurrentItem(Constants.FASHION);
                        binding.drawer.closeDrawers();
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }


    static String jsonTEST = "{\"response\":{\"status\":\"ok\",\"userTier\":\"developer\",\"total\":18,\"startIndex\":1,\"pageSize\":10,\"currentPage\":1,\"pages\":2,\"orderBy\":\"relevance\",\"results\":[" +
            "{\"id\":\"books/booksblog/2016/oct/24/poem-of-the-week-and-shuntaro-tanikawa\",\"type\":\"article\",\"sectionId\":\"books\",\"sectionName\":\"Books\",\"webPublicationDate\":\"2016-10-24T10:52:10Z\",\"webTitle\":\"Poem of the Week: And by Shuntarō Tanikawa, translated by William I Elliott and Kazuo Kawamura\",\"webUrl\":\"https://www.theguardian.com/books/booksblog/2016/oct/24/poem-of-the-week-and-shuntaro-tanikawa\",\"apiUrl\":\"https://content.guardianapis.com/books/booksblog/2016/oct/24/poem-of-the-week-and-shuntaro-tanikawa\",\"fields\":{\"trailText\":\"An illuminating offering from one of the world’s ‘active poetic volcanoes’ uses haiku influences to reflect on death’s proximity\",\"thumbnail\":\"https://media.guim.co.uk/3065a0c6189a9e7462bb19da44134db497bb8467/0_17_1369_821/500.jpg\"},\"tags\":[{\"id\":\"profile/carolrumens\",\"type\":\"contributor\",\"webTitle\":\"Carol Rumens\",\"webUrl\":\"https://www.theguardian.com/profile/carolrumens\",\"apiUrl\":\"https://content.guardianapis.com/profile/carolrumens\",\"references\":[],\"bio\":\"<p>Carol Rumens is the author of 14 collections of poems, as well as occasional fiction, drama and translation. She has received the Cholmondeley Award and the Prudence Farmer Prize, and was joint recipient of an Alice Hunt Bartlett Award. Her most recent publication is the prose book, Self into Song, based on three poetry lectures delivered in the Bloodaxe-Newcastle University Lecture Series. She is currently professor in creative writing at Bangor University, and is a fellow of the Royal Society of Literature. Her latest collection is <a href=\\\"http://www.serenbooks.com/book/de-chiricos-threads/9781854115348\\\">De Chirico's Threads, published by Seren Books</a>.</p>\",\"bylineImageUrl\":\"https://static.guim.co.uk/artsblog/authorpics/carol_rumens.jpg\",\"firstName\":\"rumens\",\"lastName\":\"carol\"}],\"isHosted\":false,\"pillarId\":\"pillar/arts\",\"pillarName\":\"Arts\"}," +
            "{\"id\":\"world/shortcuts/2015/nov/08/the-tokyo-hotel-where-guests-can-curl-up-with-1700-good-books\",\"type\":\"article\",\"sectionId\":\"world\",\"sectionName\":\"World news\",\"webPublicationDate\":\"2015-11-08T17:30:07Z\",\"webTitle\":\"The Tokyo hotel where guests can curl up with 1,700 good books\",\"webUrl\":\"https://www.theguardian.com/world/shortcuts/2015/nov/08/the-tokyo-hotel-where-guests-can-curl-up-with-1700-good-books\",\"apiUrl\":\"https://content.guardianapis.com/world/shortcuts/2015/nov/08/the-tokyo-hotel-where-guests-can-curl-up-with-1700-good-books\",\"fields\":{\"trailText\":\"Book and Bed is a Japanese hotel that’s taking a very novel approach to hospitality\",\"thumbnail\":\"https://media.guim.co.uk/2a2393ac26b77a57e97023edf514e71c4e7a2e5f/0_68_5470_3281/500.jpg\"},\"tags\":[{\"id\":\"profile/richard-smart\",\"type\":\"contributor\",\"webTitle\":\"Richard Smart\",\"webUrl\":\"https://www.theguardian.com/profile/richard-smart\",\"apiUrl\":\"https://content.guardianapis.com/profile/richard-smart\",\"references\":[]}],\"isHosted\":false,\"pillarId\":\"pillar/news\",\"pillarName\":\"News\"}," +
            "{\"id\":\"world/2014/dec/16/mcdonalds-japan-running-out-of-fries\",\"type\":\"article\",\"sectionId\":\"world\",\"sectionName\":\"World news\",\"webPublicationDate\":\"2014-12-16T04:19:07Z\",\"webTitle\":\"McDonald's Japan running out of fries\",\"webUrl\":\"https://www.theguardian.com/world/2014/dec/16/mcdonalds-japan-running-out-of-fries\",\"apiUrl\":\"https://content.guardianapis.com/world/2014/dec/16/mcdonalds-japan-running-out-of-fries\",\"fields\":{\"trailText\":\"Customers restricted to small portions as as shipping disputes in the US forces chain to downsize meals\"},\"tags\":[{\"id\":\"profile/justinmccurry\",\"type\":\"contributor\",\"webTitle\":\"Justin McCurry\",\"webUrl\":\"https://www.theguardian.com/profile/justinmccurry\",\"apiUrl\":\"https://content.guardianapis.com/profile/justinmccurry\",\"references\":[],\"bio\":\"<p>Justin McCurry is the Guardian's Tokyo correspondent.</p>\",\"bylineImageUrl\":\"https://uploads.guim.co.uk/2017/12/28/Justin-McCurry.jpg\",\"bylineLargeImageUrl\":\"https://uploads.guim.co.uk/2017/12/28/Justin_McCurry,_L.png\",\"firstName\":\"Justin\",\"lastName\":\"McCurry\"}],\"isHosted\":false,\"pillarId\":\"pillar/news\",\"pillarName\":\"News\"}," +
            "{\"id\":\"music/musicblog/2016/dec/20/the-best-albums-and-tracks-of-2016-how-our-writers-voted\",\"type\":\"article\",\"sectionId\":\"music\",\"sectionName\":\"Music\",\"webPublicationDate\":\"2016-12-20T08:58:53Z\",\"webTitle\":\"The best albums and tracks of 2016: how our writers voted\",\"webUrl\":\"https://www.theguardian.com/music/musicblog/2016/dec/20/the-best-albums-and-tracks-of-2016-how-our-writers-voted\",\"apiUrl\":\"https://content.guardianapis.com/music/musicblog/2016/dec/20/the-best-albums-and-tracks-of-2016-how-our-writers-voted\",\"fields\":{\"trailText\":\"You’ve seen our end of year list (and our track of the year) – now it’s time to look behind the scenes and discover the favourite albums and tracks of the Guardian’s music critics• More best culture of 2016\",\"thumbnail\":\"https://media.guim.co.uk/879f9b696f3e4a638f580000f3aa688961760cc6/0_0_2560_1536/500.jpg\"},\"tags\":[{\"id\":\"profile/guardianmusic\",\"type\":\"contributor\",\"webTitle\":\"Guardian music\",\"webUrl\":\"https://www.theguardian.com/profile/guardianmusic\",\"apiUrl\":\"https://content.guardianapis.com/profile/guardianmusic\",\"references\":[],\"bio\":\"<p>Guardian music bring you the latest music news, reviews, videos, and interviews as well as live performances, documentaries, discussions, and festival clips. <a href=\\\"https://plus.google.com/104221407662350228796/posts\\\" rel=\\\"author\\\">Follow Guardian music on Google+ </a></p>\",\"firstName\":\"guardian.co.uk/music\",\"lastName\":\"\",\"twitterHandle\":\"guardianmusic\"}],\"isHosted\":false,\"pillarId\":\"pillar/arts\",\"pillarName\":\"Arts\"}," +
            "{\"id\":\"science/2013/dec/22/david-attenborough-jurassic-coast-theme-park\",\"type\":\"article\",\"sectionId\":\"science\",\"sectionName\":\"Science\",\"webPublicationDate\":\"2013-12-22T16:44:35Z\",\"webTitle\":\"David Attenborough backs £85m Jurassic coast theme park\",\"webUrl\":\"https://www.theguardian.com/science/2013/dec/22/david-attenborough-jurassic-coast-theme-park\",\"apiUrl\":\"https://content.guardianapis.com/science/2013/dec/22/david-attenborough-jurassic-coast-theme-park\",\"fields\":{\"trailText\":\"Former quarry in Dorset lined up as site for Jurassica project showcasing prehistory of England's south coast\"},\"tags\":[{\"id\":\"profile/jamesmeikle\",\"type\":\"contributor\",\"webTitle\":\"James Meikle\",\"webUrl\":\"https://www.theguardian.com/profile/jamesmeikle\",\"apiUrl\":\"https://content.guardianapis.com/profile/jamesmeikle\",\"references\":[],\"bio\":\"<p>James Meikle is a former special correspondent for the Guardian</p>\",\"bylineImageUrl\":\"https://static.guim.co.uk/sys-images/Guardian/Pix/contributor/2007/09/28/james_meikle_140x140.jpg\",\"firstName\":\"meikle\",\"lastName\":\"\"}],\"isHosted\":false,\"pillarId\":\"pillar/news\",\"pillarName\":\"News\"}," +
            "{\"id\":\"world/gallery/2013/dec/17/central-african-republic-violence-photographs-jerome-delay\",\"type\":\"gallery\",\"sectionId\":\"world\",\"sectionName\":\"World news\",\"webPublicationDate\":\"2013-12-17T18:15:44Z\",\"webTitle\":\"Central African Republic violence - photographs by Jerome Delay\",\"webUrl\":\"https://www.theguardian.com/world/gallery/2013/dec/17/central-african-republic-violence-photographs-jerome-delay\",\"apiUrl\":\"https://content.guardianapis.com/world/gallery/2013/dec/17/central-african-republic-violence-photographs-jerome-delay\",\"fields\":{\"trailText\":\"Associated Press photographer Jerome Delay has been covering the ongoing crisis in the Central African Republic\"},\"tags\":[],\"isHosted\":false,\"pillarId\":\"pillar/news\",\"pillarName\":\"News\"}," +
            "{\"id\":\"news/gallery/2014/jan/05/the-weekend-in-pictures\",\"type\":\"gallery\",\"sectionId\":\"news\",\"sectionName\":\"News\",\"webPublicationDate\":\"2014-01-05T16:14:00Z\",\"webTitle\":\"The weekend in pictures\",\"webUrl\":\"https://www.theguardian.com/news/gallery/2014/jan/05/the-weekend-in-pictures\",\"apiUrl\":\"https://content.guardianapis.com/news/gallery/2014/jan/05/the-weekend-in-pictures\",\"fields\":{\"trailText\":\"<p>From storms to meteor showers – a selection of some of the best images from around the world this weekend</p>\"},\"tags\":[{\"id\":\"profile/jo-blason\",\"type\":\"contributor\",\"webTitle\":\"Jo Blason\",\"webUrl\":\"https://www.theguardian.com/profile/jo-blason\",\"apiUrl\":\"https://content.guardianapis.com/profile/jo-blason\",\"references\":[],\"bio\":\"<p>Jo Blason is assistant picture editor for <a href=\\\"http://www.theguardian.com\\\">guardian.com</a> and <a href=\\\"http://www.theguardian.com/cities\\\">cities</a></p>\",\"firstName\":\"blason\",\"lastName\":\"jo\"}],\"isHosted\":false,\"pillarId\":\"pillar/news\",\"pillarName\":\"News\"}," +
            "{\"id\":\"world/gallery/2013/dec/11/central-african-republic-conflict-in-pictures\",\"type\":\"gallery\",\"sectionId\":\"world\",\"sectionName\":\"World news\",\"webPublicationDate\":\"2013-12-11T19:45:00Z\",\"webTitle\":\"Central African Republic conflict – in pictures\",\"webUrl\":\"https://www.theguardian.com/world/gallery/2013/dec/11/central-african-republic-conflict-in-pictures\",\"apiUrl\":\"https://content.guardianapis.com/world/gallery/2013/dec/11/central-african-republic-conflict-in-pictures\",\"fields\":{\"trailText\":\"<p>French soldiers are trying to disarm between former rebels and militias who have sown terror in the CAR, especially the capital, Bangui</p>\"},\"tags\":[],\"isHosted\":false,\"pillarId\":\"pillar/news\",\"pillarName\":\"News\"}," +
            "{\"id\":\"music/2012/jul/11/bournemouth-symphony-orchestra-london-2012-festival\",\"type\":\"article\",\"sectionId\":\"music\",\"sectionName\":\"Music\",\"webPublicationDate\":\"2012-07-11T17:41:33Z\",\"webTitle\":\"How Dorset's 1000-year-old Viking bones inspired my new oratorio\",\"webUrl\":\"https://www.theguardian.com/music/2012/jul/11/bournemouth-symphony-orchestra-london-2012-festival\",\"apiUrl\":\"https://content.guardianapis.com/music/2012/jul/11/bournemouth-symphony-orchestra-london-2012-festival\",\"fields\":{\"trailText\":\"<p><strong>Stephen McNeff: </strong>As the BSO's composer, I was tasked with writing a piece for the London 2012 festival. And it was the skeletons in Dorset's cupboard that inspired The Chalk Legend</p>\"},\"tags\":[],\"isHosted\":false,\"pillarId\":\"pillar/arts\",\"pillarName\":\"Arts\"}," +
            "{\"id\":\"film/2018/jan/28/12-strong-review-chris-hemsworth-doped-up-action\",\"type\":\"article\",\"sectionId\":\"film\",\"sectionName\":\"Film\",\"webPublicationDate\":\"2018-01-28T08:00:13Z\",\"webTitle\":\"12 Strong review – doped-up special forces action\",\"webUrl\":\"https://www.theguardian.com/film/2018/jan/28/12-strong-review-chris-hemsworth-doped-up-action\",\"apiUrl\":\"https://content.guardianapis.com/film/2018/jan/28/12-strong-review-chris-hemsworth-doped-up-action\",\"fields\":{\"trailText\":\"Chris Hemsworth leads a team into Afghanistan in a steroid-enhanced tale that makes selective use of the facts\",\"thumbnail\":\"https://media.guim.co.uk/a272fe03d0d51f098c636aea60eb584a71a84ce1/0_142_2250_1350/500.jpg\"},\"tags\":[  ],\"isHosted\":false,\"pillarId\":\"pillar/arts\",\"pillarName\":\"Arts\"}," +
            "{\"id\":\"commentisfree/belief/2011/oct/28/robot-priest-cleverbot-pastoral-care\",\"type\":\"article\",\"sectionId\":\"commentisfree\",\"sectionName\":\"Opinion\",\"webPublicationDate\":\"2011-10-28T16:30:01Z\",\"webTitle\":\"Would you accept a robot as your priest or vicar? | Danielle Elizabeth Tumminio\",\"webUrl\":\"https://www.theguardian.com/commentisfree/belief/2011/oct/28/robot-priest-cleverbot-pastoral-care\",\"apiUrl\":\"https://content.guardianapis.com/commentisfree/belief/2011/oct/28/robot-priest-cleverbot-pastoral-care\",\"fields\":{\"trailText\":\"<strong>Danielle Elizabeth Tumminio:</strong> The Cleverbot, an artificial intelligence application, is the consummate copycat, but could it take over pastoral care?\"},\"tags\":[{\"id\":\"profile/danielle-elizabeth-tumminio\",\"type\":\"contributor\",\"webTitle\":\"Danielle Elizabeth Tumminio\",\"webUrl\":\"https://www.theguardian.com/profile/danielle-elizabeth-tumminio\",\"apiUrl\":\"https://content.guardianapis.com/profile/danielle-elizabeth-tumminio\",\"references\":[],\"bio\":\"<p>Danielle Elizabeth Tumminio is an Episcopal priest in Boston, Massachusetts.  She lectures part-time at Yale University and is the author of God and Harry Potter at Yale</p>\",\"bylineImageUrl\":\"https://static.guim.co.uk/sys-images/Guardian/Pix/pictures/2011/5/19/1305818983391/Danielle-Elizabeth-Tumminio.jpg\",\"firstName\":\"danielle\",\"lastName\":\"elizabethtumminio\"}],\"isHosted\":false,\"pillarId\":\"pillar/opinion\",\"pillarName\":\"Opinion\"}]}}";

}
