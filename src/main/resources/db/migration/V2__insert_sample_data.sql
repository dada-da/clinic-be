-- ============================================
-- Sample Data Insertion - Simplified Version
-- Version: 2.0
-- Description: Core sample data without medicine/prescription tables
-- ============================================

-- ============================================
-- Sample Doctors (15 doctors across specialties)
-- ============================================
INSERT INTO `doctor` (`first_name`, `last_name`, `specialty`, `phone`, `email`) VALUES
-- General Practitioners
('John', 'Smith', 'GENERAL', '555-0101', 'john.smith@clinic.com'),
('Sarah', 'Johnson', 'GENERAL', '555-0102', 'sarah.johnson@clinic.com'),
('Michael', 'Williams', 'GENERAL', '555-0103', 'michael.williams@clinic.com'),

-- Cardiologists
('Emily', 'Brown', 'CARDIOLOGY', '555-0201', 'emily.brown@clinic.com'),
('David', 'Jones', 'CARDIOLOGY', '555-0202', 'david.jones@clinic.com'),

-- Pediatricians
('Jennifer', 'Garcia', 'PEDIATRICS', '555-0301', 'jennifer.garcia@clinic.com'),
('Robert', 'Martinez', 'PEDIATRICS', '555-0302', 'robert.martinez@clinic.com'),
('Lisa', 'Rodriguez', 'PEDIATRICS', '555-0303', 'lisa.rodriguez@clinic.com'),

-- Dermatologists
('James', 'Wilson', 'DERMATOLOGY', '555-0401', 'james.wilson@clinic.com'),
('Maria', 'Anderson', 'DERMATOLOGY', '555-0402', 'maria.anderson@clinic.com'),

-- Orthopedics
('William', 'Taylor', 'ORTHOPEDICS', '555-0501', 'william.taylor@clinic.com'),
('Patricia', 'Thomas', 'ORTHOPEDICS', '555-0502', 'patricia.thomas@clinic.com'),

-- Neurologists
('Richard', 'Moore', 'NEUROLOGY', '555-0601', 'richard.moore@clinic.com'),
('Linda', 'Jackson', 'NEUROLOGY', '555-0602', 'linda.jackson@clinic.com'),
('Christopher', 'White', 'NEUROLOGY', '555-0603', 'christopher.white@clinic.com');

-- ============================================
-- Sample Patients (30 patients with social_id)
-- ============================================
INSERT INTO `patient` (`social_id`, `full_name`, `dob`, `gender`, `address`, `phone`, `email`) VALUES
                                                                                                   ('SID001985315', 'Alice Anderson', '1985-03-15', 'FEMALE', '123 Main St, Springfield, IL 62701', '555-1001', 'alice.anderson@email.com'),
                                                                                                   ('SID002990722', 'Bob Baker', '1990-07-22', 'MALE', '456 Oak Ave, Chicago, IL 60601', '555-1002', 'bob.baker@email.com'),
                                                                                                   ('SID003978130', 'Carol Chen', '1978-11-30', 'FEMALE', '789 Pine Rd, Naperville, IL 60540', '555-1003', 'carol.chen@email.com'),
                                                                                                   ('SID004200510', 'David Davis', '2000-05-10', 'MALE', '321 Elm St, Peoria, IL 61602', '555-1004', 'david.davis@email.com'),
                                                                                                   ('SID005995918', 'Emma Evans', '1995-09-18', 'FEMALE', '654 Maple Dr, Rockford, IL 61101', '555-1005', 'emma.evans@email.com'),
                                                                                                   ('SID006982228', 'Frank Foster', '1982-02-28', 'MALE', '987 Cedar Ln, Aurora, IL 60505', '555-1006', 'frank.foster@email.com'),
                                                                                                   ('SID007970125', 'Grace Green', '1970-12-05', 'FEMALE', '147 Birch Ave, Joliet, IL 60435', '555-1007', 'grace.green@email.com'),
                                                                                                   ('SID008201580', 'Henry Harris', '2015-08-20', 'MALE', '258 Ash Ct, Elgin, IL 60120', '555-1008', 'henry.harris@email.com'),
                                                                                                   ('SID009201041', 'Isabel Ivory', '2010-04-12', 'FEMALE', '369 Willow Way, Waukegan, IL 60085', '555-1009', 'isabel.ivory@email.com'),
                                                                                                   ('SID010988625', 'Jack Johnson', '1988-06-25', 'MALE', '741 Oak Park Blvd, Cicero, IL 60804', '555-1010', 'jack.johnson@email.com'),
                                                                                                   ('SID011993114', 'Karen King', '1993-01-14', 'FEMALE', '852 Lake St, Evanston, IL 60201', '555-1011', 'karen.king@email.com'),
                                                                                                   ('SID012975108', 'Leo Lopez', '1975-10-08', 'MALE', '963 River Rd, Schaumburg, IL 60173', '555-1012', 'leo.lopez@email.com'),
                                                                                                   ('SID013968322', 'Mary Martinez', '1968-03-22', 'FEMALE', '159 Hill Dr, Bolingbrook, IL 60440', '555-1013', 'mary.martinez@email.com'),
                                                                                                   ('SID014200571', 'Nathan Nelson', '2005-07-19', 'MALE', '357 Valley View, Palatine, IL 60067', '555-1014', 'nathan.nelson@email.com'),
                                                                                                   ('SID015992111', 'Olivia O''Connor', '1992-11-11', 'FEMALE', '468 Summit Ave, Skokie, IL 60076', '555-1015', 'olivia.oconnor@email.com'),
                                                                                                   ('SID016987502', 'Peter Parker', '1987-05-02', 'MALE', '579 Highland Rd, Des Plaines, IL 60016', '555-1016', 'peter.parker@email.com'),
                                                                                                   ('SID017980930', 'Quinn Roberts', '1980-09-30', 'OTHER', '680 Forest Ln, Mount Prospect, IL 60056', '555-1017', 'quinn.roberts@email.com'),
                                                                                                   ('SID018998214', 'Rachel Rivera', '1998-02-14', 'FEMALE', '791 Park Pl, Wheaton, IL 60187', '555-1018', 'rachel.rivera@email.com'),
                                                                                                   ('SID019972125', 'Samuel Scott', '1972-12-25', 'MALE', '802 Garden St, Oak Park, IL 60302', '555-1019', 'samuel.scott@email.com'),
                                                                                                   ('SID020200860', 'Tina Turner', '2008-06-08', 'FEMALE', '913 Spring Rd, Berwyn, IL 60402', '555-1020', 'tina.turner@email.com'),
                                                                                                   ('SID021984417', 'Uma Patel', '1984-04-17', 'FEMALE', '124 Winter St, Downers Grove, IL 60515', '555-1021', 'uma.patel@email.com'),
                                                                                                   ('SID022991829', 'Victor Vega', '1991-08-29', 'MALE', '235 Summer Ave, Lombard, IL 60148', '555-1022', 'victor.vega@email.com'),
                                                                                                   ('SID023977105', 'Wendy Walsh', '1977-01-05', 'FEMALE', '346 Autumn Dr, Glen Ellyn, IL 60137', '555-1023', 'wendy.walsh@email.com'),
                                                                                                   ('SID024201210', 'Xavier Xu', '2012-10-20', 'MALE', '457 Seasons Blvd, Carol Stream, IL 60188', '555-1024', 'xavier.xu@email.com'),
                                                                                                   ('SID025986308', 'Yolanda Young', '1986-03-08', 'FEMALE', '568 Meadow Ln, Addison, IL 60101', '555-1025', 'yolanda.young@email.com'),
                                                                                                   ('SID026994716', 'Zachary Zhang', '1994-07-16', 'MALE', '679 Prairie Rd, Bloomingdale, IL 60108', '555-1026', 'zachary.zhang@email.com'),
                                                                                                   ('SID027973123', 'Amanda Adams', '1973-11-23', 'FEMALE', '780 Creek Way, Roselle, IL 60172', '555-1027', 'amanda.adams@email.com'),
                                                                                                   ('SID028200153', 'Brandon Brown', '2001-05-31', 'MALE', '891 Brook St, Hanover Park, IL 60133', '555-1028', 'brandon.brown@email.com'),
                                                                                                   ('SID029989909', 'Catherine Clark', '1989-09-09', 'FEMALE', '902 Stream Ave, Bartlett, IL 60103', '555-1029', 'catherine.clark@email.com'),
                                                                                                   ('SID030976202', 'Daniel Douglas', '1976-02-02', 'MALE', '113 Pond Ct, Glendale Heights, IL 60139', '555-1030', 'daniel.douglas@email.com');

-- ============================================
-- Sample Appointments (40 appointments - mix of statuses)
-- ============================================
INSERT INTO `appointment` (`patient_id`, `doctor_id`, `date_time`, `reason`, `status`) VALUES
-- Past completed appointments (with medical records)
(1, 1, '2024-10-15 09:00:00', 'Annual checkup', 'COMPLETED'),
(2, 4, '2024-10-15 10:30:00', 'Chest pain evaluation', 'COMPLETED'),
(3, 6, '2024-10-16 14:00:00', 'Cold symptoms', 'COMPLETED'),
(4, 2, '2024-10-17 11:00:00', 'Persistent headache', 'COMPLETED'),
(5, 9, '2024-10-18 15:30:00', 'Skin rash', 'COMPLETED'),
(6, 11, '2024-10-19 09:30:00', 'Knee injury', 'COMPLETED'),
(7, 13, '2024-10-20 10:00:00', 'Memory concerns', 'COMPLETED'),
(8, 6, '2024-10-21 14:30:00', 'Ear pain', 'COMPLETED'),
(9, 7, '2024-10-22 11:30:00', 'Sore throat', 'COMPLETED'),
(10, 1, '2024-10-23 16:00:00', 'Stomach issues', 'COMPLETED'),
(11, 5, '2024-10-24 09:00:00', 'Heart palpitations', 'COMPLETED'),
(12, 10, '2024-10-25 13:00:00', 'Back pain', 'COMPLETED'),
(13, 3, '2024-10-26 10:30:00', 'Blood sugar check', 'COMPLETED'),
(14, 8, '2024-10-27 15:00:00', 'Acne treatment', 'COMPLETED'),
(15, 14, '2024-10-28 11:00:00', 'Numbness in extremities', 'COMPLETED'),

-- Recent completed appointments
(16, 2, '2024-11-10 09:00:00', 'Allergies', 'COMPLETED'),
(17, 12, '2024-11-10 14:00:00', 'Wrist pain', 'COMPLETED'),
(18, 15, '2024-11-11 10:00:00', 'Anxiety symptoms', 'COMPLETED'),
(19, 13, '2024-11-11 15:00:00', 'Movement difficulties', 'COMPLETED'),
(20, 7, '2024-11-12 11:00:00', 'Breathing problems', 'COMPLETED'),

-- Cancelled and no-show
(21, 2, '2024-11-13 09:00:00', 'Follow-up', 'CANCELLED'),
(22, 3, '2024-11-13 14:00:00', 'Routine checkup', 'NO_SHOW'),
(23, 9, '2024-11-14 11:00:00', 'Skin examination', 'CANCELLED'),

-- Current/upcoming scheduled appointments
(1, 1, '2024-11-25 09:00:00', 'Follow-up checkup', 'SCHEDULED'),
(2, 4, '2024-11-25 10:30:00', 'Cardiology follow-up', 'SCHEDULED'),
(3, 6, '2024-11-25 14:00:00', 'Well-child visit', 'SCHEDULED'),
(5, 9, '2024-11-26 09:30:00', 'Skin follow-up', 'SCHEDULED'),
(7, 13, '2024-11-26 11:00:00', 'Neurology consultation', 'SCHEDULED'),
(10, 1, '2024-11-26 15:00:00', 'General checkup', 'SCHEDULED'),
(12, 11, '2024-11-27 10:00:00', 'Physical therapy review', 'SCHEDULED'),
(15, 14, '2024-11-27 14:30:00', 'Nerve function test', 'SCHEDULED'),
(18, 2, '2024-11-28 09:00:00', 'Mental health follow-up', 'SCHEDULED'),
(20, 7, '2024-11-28 11:30:00', 'Asthma management', 'SCHEDULED'),

-- Future appointments
(4, 3, '2024-12-02 09:00:00', 'Diabetes management', 'SCHEDULED'),
(8, 8, '2024-12-02 14:00:00', 'Dermatology consultation', 'SCHEDULED'),
(11, 10, '2024-12-03 10:30:00', 'Back pain treatment', 'SCHEDULED'),
(14, 12, '2024-12-03 15:30:00', 'Orthopedic review', 'SCHEDULED'),
(16, 15, '2024-12-04 11:00:00', 'Neurological assessment', 'SCHEDULED'),
(19, 1, '2024-12-04 14:00:00', 'Parkinsons follow-up', 'SCHEDULED'),
(23, 6, '2024-12-05 09:30:00', 'Pediatric vaccination', 'SCHEDULED'),
(25, 13, '2024-12-05 13:30:00', 'Migraine management', 'SCHEDULED');

-- ============================================
-- Sample Medical Records (20 completed appointments)
-- Each INSERT is separate to avoid multi-line string issues
-- ============================================

INSERT INTO `medical_record` (`appointment_id`, `symptoms`, `diagnosis`, `treatment`) VALUES
    (1, 'Persistent cough, fever', 'Upper respiratory tract infection', 'Amoxicillin 500mg: 1 capsule 3x daily for 7 days. Paracetamol 500mg: 1 tablet every 6 hours for fever. Rest and increase fluid intake. Follow-up in 1 week if symptoms persist.');

INSERT INTO `medical_record` (`appointment_id`, `symptoms`, `diagnosis`, `treatment`) VALUES
    (2, 'Chest pain, shortness of breath', 'Stable angina', 'Bisoprolol 5mg: 1 tablet daily in the morning. Aspirin 100mg: 1 tablet daily (blood thinner). Lifestyle modifications: reduce stress, low-salt diet. ECG performed - abnormal findings. Schedule stress test. Follow-up in 2 weeks.');

INSERT INTO `medical_record` (`appointment_id`, `symptoms`, `diagnosis`, `treatment`) VALUES
    (3, 'Runny nose, sneezing, fever', 'Common cold (viral infection)', 'Symptomatic treatment only. Rest and hydration. No antibiotics needed (viral infection). Over-the-counter pain relievers as needed. Should resolve in 5-7 days.');

INSERT INTO `medical_record` (`appointment_id`, `symptoms`, `diagnosis`, `treatment`) VALUES
    (4, 'Headache, fatigue', 'Tension headache', 'Ibuprofen 400mg: 1 tablet 3x daily with food. Stress management techniques recommended. Adequate sleep (7-8 hours). Regular exercise. Consider relaxation therapy.');

INSERT INTO `medical_record` (`appointment_id`, `symptoms`, `diagnosis`, `treatment`) VALUES
    (5, 'Itchy rash on arms', 'Contact dermatitis', 'Hydrocortisone cream 1%: Apply thin layer twice daily for 2 weeks. Avoid known allergens. Use fragrance-free soaps. Moisturize regularly. Follow-up in 2 weeks if no improvement.');

INSERT INTO `medical_record` (`appointment_id`, `symptoms`, `diagnosis`, `treatment`) VALUES
    (6, 'Knee pain after fall', 'Sprained medial collateral ligament', 'RICE protocol: Rest, Ice, Compression, Elevation. Ibuprofen 400mg: 1 tablet 3x daily for pain. Knee brace recommended. Physical therapy referral. No weight-bearing activities for 2 weeks. Follow-up in 1 week.');

INSERT INTO `medical_record` (`appointment_id`, `symptoms`, `diagnosis`, `treatment`) VALUES
    (7, 'Memory issues, confusion', 'Mild cognitive impairment', 'Cognitive function tests completed. Vitamin D3 1000IU: 1 tablet daily. Vitamin B Complex: 1 tablet daily. Mental exercises recommended. Referral to neuropsychology for full evaluation. Follow-up in 1 month.');

INSERT INTO `medical_record` (`appointment_id`, `symptoms`, `diagnosis`, `treatment`) VALUES
    (8, 'Ear pain, reduced hearing', 'Acute otitis media', 'Amoxicillin 500mg: 1 capsule twice daily for 10 days. Paracetamol 500mg: For pain relief as needed. Keep ear dry. No swimming until infection clears. Follow-up in 1 week.');

INSERT INTO `medical_record` (`appointment_id`, `symptoms`, `diagnosis`, `treatment`) VALUES
    (9, 'Sore throat, difficulty swallowing', 'Viral pharyngitis', 'Throat examination: inflamed, no exudate (viral). Symptomatic treatment only. Warm salt water gargle 3-4x daily. Lozenges for throat pain. Increase fluids. Rest. Should improve in 3-5 days.');

INSERT INTO `medical_record` (`appointment_id`, `symptoms`, `diagnosis`, `treatment`) VALUES
    (10, 'Abdominal pain, nausea', 'Gastritis', 'Omeprazole 20mg: 1 capsule daily before breakfast for 4 weeks. Dietary modifications: avoid spicy foods, caffeine, alcohol. Small, frequent meals. Avoid NSAIDs. Stress reduction. Follow-up in 2 weeks.');

INSERT INTO `medical_record` (`appointment_id`, `symptoms`, `diagnosis`, `treatment`) VALUES
    (11, 'Irregular heartbeat, dizziness', 'Atrial fibrillation', 'Clopidogrel 75mg: 1 tablet daily (antiplatelet). Warfarin 5mg: Take as directed (anticoagulant, INR monitoring required). ECG shows atrial fibrillation. Cardiology referral scheduled. Monitor heart rate. Avoid excessive caffeine. Urgent follow-up in 3 days.');

INSERT INTO `medical_record` (`appointment_id`, `symptoms`, `diagnosis`, `treatment`) VALUES
    (12, 'Back pain, muscle stiffness', 'Lumbar muscle strain', 'Ibuprofen 400mg: 1 tablet 3x daily with food. Naproxen 250mg: 1 tablet twice daily if needed. Physical therapy referral. Heat/cold therapy. Gentle stretching exercises. Avoid heavy lifting. Follow-up in 2 weeks.');

INSERT INTO `medical_record` (`appointment_id`, `symptoms`, `diagnosis`, `treatment`) VALUES
    (13, 'Elevated blood sugar, thirst, frequent urination', 'Type 2 diabetes mellitus (newly diagnosed)', 'Metformin 500mg: 1 tablet twice daily with meals. Blood glucose monitoring: check fasting glucose daily. Dietary consultation scheduled. Exercise: 30 minutes daily walking. Weight loss goal: 10% body weight. HbA1c test scheduled. Follow-up in 2 weeks.');

INSERT INTO `medical_record` (`appointment_id`, `symptoms`, `diagnosis`, `treatment`) VALUES
    (14, 'Acne breakout on face', 'Acne vulgaris (moderate)', 'Tretinoin gel 0.025%: Apply small amount nightly (may cause initial dryness). Gentle cleanser twice daily. Oil-free moisturizer. Avoid touching face. No picking or squeezing. Sunscreen daily (tretinoin increases sun sensitivity). Follow-up in 6 weeks.');

INSERT INTO `medical_record` (`appointment_id`, `symptoms`, `diagnosis`, `treatment`) VALUES
    (15, 'Tingling in hands and feet', 'Peripheral neuropathy due to B12 deficiency', 'Vitamin B12 injection: 1000mcg weekly for 4 weeks, then monthly. Vitamin B Complex: 1 tablet daily. Iron 65mg: 1 tablet three times daily. Blood tests show low B12. Dietary advice: increase B12-rich foods. Follow-up in 4 weeks.');

INSERT INTO `medical_record` (`appointment_id`, `symptoms`, `diagnosis`, `treatment`) VALUES
    (16, 'Sneezing, itchy eyes, runny nose', 'Allergic rhinitis (seasonal allergies)', 'Cetirizine 10mg: 1 tablet daily. Avoid outdoor activities during high pollen count. Keep windows closed. Use air purifier. Shower after being outdoors. Consider allergy testing if symptoms persist.');

INSERT INTO `medical_record` (`appointment_id`, `symptoms`, `diagnosis`, `treatment`) VALUES
    (17, 'Wrist pain, numbness', 'Carpal tunnel syndrome', 'Ibuprofen 400mg: 1 tablet twice daily with food. Wrist splint: wear at night. Ergonomic workplace assessment. Reduce repetitive hand movements. Ice therapy. Physical therapy referral. Consider cortisone injection if no improvement. Follow-up in 3 weeks.');

INSERT INTO `medical_record` (`appointment_id`, `symptoms`, `diagnosis`, `treatment`) VALUES
    (18, 'Anxiety, insomnia, restlessness', 'Generalized anxiety disorder', 'Fluoxetine 20mg: 1 tablet at bedtime (SSRI antidepressant). Alprazolam 0.5mg: 1 tablet twice daily as needed (short-term only). Counseling/therapy referral. Relaxation techniques. Regular exercise. Sleep hygiene education. Follow-up in 2 weeks.');

INSERT INTO `medical_record` (`appointment_id`, `symptoms`, `diagnosis`, `treatment`) VALUES
    (19, 'Tremors, balance issues, slow movement', 'Early Parkinson disease', 'Prednisolone 5mg: Take as directed (dosage to be adjusted). Neurological assessment completed. Physical therapy for balance. Occupational therapy referral. Fall prevention strategies. Support group information provided. Movement disorder specialist referral. Follow-up in 2 weeks.');

INSERT INTO `medical_record` (`appointment_id`, `symptoms`, `diagnosis`, `treatment`) VALUES
    (20, 'Persistent cough, wheezing, shortness of breath', 'Asthma exacerbation', 'Salbutamol inhaler: 2 puffs twice daily (bronchodilator). Montelukast 10mg: 1 tablet daily at bedtime. Prednisolone 5mg: 1 tablet daily for 5 days (oral steroid). Inhaler technique reviewed and corrected. Avoid triggers: smoke, cold air. Peak flow monitoring. Action plan provided. Follow-up in 1 week.');
