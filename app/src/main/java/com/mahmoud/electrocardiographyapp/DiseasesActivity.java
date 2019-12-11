package com.mahmoud.electrocardiographyapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class DiseasesActivity extends AppCompatActivity {

    List<Disease> diseases;
    EditText edit_desSearch;
    RecyclerView recycler;
    DiseaseAdupter diseaseAdupter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diseases);

        recycler = findViewById(R.id.recycler);
        edit_desSearch = findViewById(R.id.edit_desSearch);

        diseases = new ArrayList<>();

        storeData();

        diseaseAdupter = new DiseaseAdupter(this);

        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(diseaseAdupter);

        showDate("");
        edit_desSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                showDate(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }

    private void storeData() {
        diseases.add(new Disease("Left bundle branch block disease (LBBB)", "To beat properly, the heart’s tissue conducts electrical impulses throughout the muscle in a regular pattern. However, if an area of this pattern is blocked near the heart’s ventricles, the electric impulse must travel slightly longer to reach its endpoint. This makes it harder for the heart to pump blood throughout your body. Doctors call the resulting electrical pattern bundle branch block because the electrical impulse encounters a roadblock at the left or right branch of the “bundle of His.” The bundle of His is an area of the heart that conducts impulses to the left and right ventricles.\n" +
                "Left bundle branch block (LBBB) is a blockage of electrical impulses to the heart’s left ventricle. This is the lower-left portion of the heart. LBBB doesn’t always cause symptoms. In fact, some people have it for years and never know it. For others, however, a delay in the arrival of electrical impulses to the heart’s left ventricle can cause syncope, which is fainting due to unusual heart rhythms that affect blood pressure. Some people might also experience something called presyncope. This involves feeling like you’re about to faint, but never actually fainting. According to the Texas Heart Institute, a bundle branch block on the left may also increase your risk of developing heart disease.\n" +
                "A variety of things can cause LBBB. For example, a heart attack can damage your heart tissue, making it harder for it to conduct electricity. This can result in bundle branch block at either the right or left ventricle. Other conditions that can cause an LBBB include:\n" +
                "coronary artery disease, heart failure, high blood pressure and problems with the aortic valve. \n"));

        diseases.add(new Disease("Right Bundle Branch Block disease (RBBB)", "Right bundle branch block comes from a problem with the heart’s ability to conduct electrical signals. It usually doesn’t cause symptoms unless you have some other heart condition. Your heart has 4 chambers. The 2 upper chambers are called atria. The 2 lower chambers are called ventricles. In a healthy heart, the electrical signal for your heartbeat starts in the sinoatrial (SA) node. The SA node is found in the right atrium, the upper right chamber of the heart. From there, the signal is carried to the left atrium and travels to the lower chambers (the right and left ventricles) of the heart. As the signal travels, it triggers nearby parts of the heart muscle to contract in a coordinated manner. Two special groups of fibers called bundle branches carry the signal from the atria (top of the heart) to the ventricles (bottom of the heart). These are termed right bundle branch and left bundle branch. In right bundle branch block, there is a problem with the right branch of the conducting system that sends the electrical signal to the right ventricle. The electrical signal can’t travel down this path the way it normally would. The signal still gets to the right ventricle, but the signal has to travel to the left side before getting to the right ventricle. This takes more time. Because of this, the right ventricle contracts a little later than it normally would. This can cause the heart to eject slightly less blood. Right bundle branch block is rare in healthy young people. It happens more often in older people. This may be due to the normal changes in the heart's conduction system that occurs with age. Right bundle branch block can also occur in people who have another underlying heart or lung problem. It may also be caused as a result of a heart procedure"));

        diseases.add(new Disease("Ventricular flutter (VFL)", "Ventricular flutter appears on the ECG as a sine wave pattern that is characterized by regular, large oscillations (see Fig. 6.10). The rhythm may be difficult to distinguish from rapid VT and can progress to ventricular fibrillation. Ventricular fibrillation is a terminal arrhythmia in which ventricular contractions are uncoordinated and too weak to eject blood. The ECG shows irregular, chaotic deflections of varying amplitude and shape. Immediate defibrillation and cardiopulmonary resuscitation are necessary. Both ventricular flutter and ventricular fibrillation generally result in loss of consciousness and are usually fatal within minutes unless intervention is successful (Miller et al., 1999)."));

        diseases.add(new Disease("Idioventricular rhythm", "Normally, the pacemaker of the heart that is responsible for triggering each heart beat (ventricular contraction) is the SA (Sino Atrial) node. However, if the ventricle does not receive triggering signals at a rate high enough from either the SA node or the AV (Atrioventricular) node, the ventricular myocardium itself becomes the pacemaker (escape rhythm). This is called Idioventricular Rhythm. Ventricular signals are transmitted cell-to-cell between cardiomyocytes and not by the conduction system, creating wide sometimes bizarre QRS complexes(> 0.12 sec). The rate is usually 20-40 bpm. If the rate is >40 bpm, it is called accelerated idioventricular rhythm. The rate of 20-40 is the \"intrinsic automaticity\" of the ventricular myocardium. It can be regarded as a physiological redundancy of the cardiac electrical system."));

        diseases.add(new Disease("PR heart disease", "In electrocardiography, the PR interval is the period, measured in milliseconds, that extends from the beginning of the P wave (the onset of atrial depolarization) until the beginning of the QRS complex (the onset of ventricular depolarization); it is normally between 120 and 200ms in duration. The PR interval is sometimes termed the PQ interval. \n" +
                "Variations in the PR interval can be associated with certain medical conditions: A long PR interval (of over 200 ms) may indicate a first-degree heart block. Prolongation can be associated with hypokalemia, acute rheumatic fever, or carditis associated with Lyme disease.\n" +
                "A short PR interval (of less than 120ms) may be associated with an atrioventricular reentrant tachycardia (such as Wolff–Parkinson–White syndrome or Lawn–Ganong–Levine syndrome) or junctional rhythm.\n" +
                "A variable PR interval may indicate other types of heart block.\n" +
                "PR segment depression may indicate atrial injury or pericarditis.\n" +
                "Variable morphologies of P waves in a single EKG lead is suggestive of an ectopic pacemaker rhythm such as wandering pacemaker or multifocal atrial tachycardia."));

        diseases.add(new Disease("NORMAL SINUS HEART RATE", "The normal heart rate has been considered to be between 60 and 100 beats per minute, although there is some disagreement with regard to the normal rate in adults. The range (defined by 1st and 99th percentiles) is between 43 and 102 beats per minute in men and between 47 and 103 beats per minute in women. There is also important variability in age in young children. The normal heart rate is 110 to 150 beats per minute in infants, with gradual slowing over the first six years of life.\n" +
                "A variety of pharmacologic agents and physiologic conditions can result in changes to the normal sinus heart rate. These conditions are discussed in greater detail separately." +
                "\n" +
                "The normal heart rate increases with exertion and decreases following the cessation of activity. The rate at which the heart rate returns to baseline following exercise can have prognostic importance."));

        diseases.add(new Disease("Atrial Premature Beats(APB)", "Atrial premature beats occur in many healthy people and rarely cause symptoms. Atrial premature beats are common among people who have lung disorders. and are more common among older people than among younger people. These beats may be caused or worsened by consuming coffee, tea, or alcohol and by using some cold, hay fever, and asthma remedies.\n" +
                "Atrial premature beats only rarely cause symptoms. Sometimes the person is aware of the heat beat(palpitations)."));

        diseases.add(new Disease("Atrial flutter(AFL)", "Atrial flutter (AFL) is a common abnormal heart rhythm, similar to atrial fibrillation, the most common abnormal heart rhythm. Both conditions are types of supraventricular (above the ventricles) tachycardia (rapid heart beat). In AFL, the upper chambers (atria) of the heart beat too fast, which results in atrial muscle contractions that are faster than and out of sync with the lower chambers (ventricles)."));

        diseases.add(new Disease("Atrial fibrillation(AFIB)", "Atrial fibrillation is an irregular and often rapid heart rate that can increase your risk of strokes, heart failure and other heart-related complications.\n" +
                "During atrial fibrillation, the heart's two upper chambers (the atria) beat chaotically and irregularly — out of coordination with the two lower chambers (the ventricles) of the heart. Atrial fibrillation symptoms often include heart palpitations, shortness of breath and weakness."));

        diseases.add(new Disease("Wolff-parkinson-white(WPW)", "In Wolff-Parkinson-White (WPW) syndrome, an extra electrical pathway between your heart's upper and lower chambers causes a rapid heartbeat. The extra pathway is present at birth and fairly rare.\n" +
                "The episodes of fast heartbeats usually aren't life-threatening, but serious heart problems can occur. Treatment can stop or prevent episodes of fast heartbeats. A catheter-based procedure (ablation) can permanently correct the heart rhythm problems."));

        diseases.add(new Disease("Premature Ventricular Contractions (PVCs)", "Premature ventricular contractions (PVCs) are extra, abnormal heartbeats that begin in the ventricles, or lower pumping chambers, and disrupt your regular heart rhythm, sometimes causing you to feel a skipped beat or palpitations. PVCs — also called also called premature ventricular complexes, ventricular premature beats and extrasystoles — are very common and usually harmless."));

        diseases.add(new Disease("Bigeminy", "Bigeminy is the technical term for the sensation of the heart skipping a beat, which is also sometimes described as heart palpitations or fluttering. While the heart does not literally skip a beat, it may feel that way. The reason is that the heart's rhythm has become irregular and the movement of blood through it is different to what a person expects.\n" +
                "Most people will experience bigeminy at some point in their lives, and, mostly, it is not a cause for concern."));

        diseases.add(new Disease("Trigeminy", "In a typical heart rhythm, the heart beats in an even pattern. The top part of the heart squeezes, then the bottom part of the heart squeezes in regular rhythm. However, sometimes the bottom part of the heart (the ventricles) can beat slightly out of rhythm. Most people with trigeminy don’t have symptoms with their heart rhythm. They may go their entire lives having occasional or constant episodes of trigeminy and never know it."));

        diseases.add(new Disease("Ventricular tachycardia(VT)", "Ventricular tachycardia (VT or V-tach) is a type of abnormal heart rhythm, or arrhythmia. It occurs when the lower chamber of the heart beats too fast to pump well and the body doesn't receive enough oxygenated blood. Ventricular tachycardia may be brief, lasting for only a few seconds, and perhaps not cause any symptoms. Or it can last for much longer and cause symptoms such as dizziness, lightheadedness, palpitations or even loss of consciousness."));

    }

    private void showDate(final String i) {

        if (i.equals("")) {
            diseaseAdupter.setitemsList(diseases);
        } else if (!i.isEmpty()) {
           List<Disease> diseaseList=new ArrayList<>();
           for (int j=0;j<diseases.size();j++){

               if (diseases.get(j).getName().toLowerCase().startsWith(i.toLowerCase())){
                   diseaseList.add(diseases.get(j));
               }
           }
            diseaseAdupter.setitemsList(diseaseList);
        }


    }

}
