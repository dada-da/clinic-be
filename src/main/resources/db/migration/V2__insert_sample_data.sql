-- ============================================
-- Sample Data Insertion
-- Version: 2.0
-- Description: Insert comprehensive sample data
-- ============================================

-- ============================================
-- Sample Doctors (15 doctors across specialties)
-- ============================================
INSERT INTO `doctor` (`first_name`, `last_name`, `specialty`, `phone`) VALUES
-- General Practitioners
('John', 'Smith', 'GENERAL', '555-0101'),
('Sarah', 'Johnson', 'GENERAL', '555-0102'),
('Michael', 'Williams', 'GENERAL', '555-0103'),

-- Cardiologists
('Emily', 'Brown', 'CARDIOLOGY', '555-0201'),
('David', 'Jones', 'CARDIOLOGY', '555-0202'),

-- Pediatricians
('Jennifer', 'Garcia', 'PEDIATRICS', '555-0301'),
('Robert', 'Martinez', 'PEDIATRICS', '555-0302'),
('Lisa', 'Rodriguez', 'PEDIATRICS', '555-0303'),

-- Dermatologists
('James', 'Wilson', 'DERMATOLOGY', '555-0401'),
('Maria', 'Anderson', 'DERMATOLOGY', '555-0402'),

-- Orthopedics
('William', 'Taylor', 'ORTHOPEDICS', '555-0501'),
('Patricia', 'Thomas', 'ORTHOPEDICS', '555-0502'),

-- Neurologists
('Richard', 'Moore', 'NEUROLOGY', '555-0601'),
('Linda', 'Jackson', 'NEUROLOGY', '555-0602'),
('Christopher', 'White', 'NEUROLOGY', '555-0603');

-- ============================================
-- Sample Patients (30 patients with diverse demographics)
-- ============================================
INSERT INTO `patient` (`full_name`, `dob`, `gender`, `address`, `phone`) VALUES
                                                                             ('Alice Anderson', '1985-03-15', 'FEMALE', '123 Main St, Springfield, IL 62701', '555-1001'),
                                                                             ('Bob Baker', '1990-07-22', 'MALE', '456 Oak Ave, Chicago, IL 60601', '555-1002'),
                                                                             ('Carol Chen', '1978-11-30', 'FEMALE', '789 Pine Rd, Naperville, IL 60540', '555-1003'),
                                                                             ('David Davis', '2000-05-10', 'MALE', '321 Elm St, Peoria, IL 61602', '555-1004'),
                                                                             ('Emma Evans', '1995-09-18', 'FEMALE', '654 Maple Dr, Rockford, IL 61101', '555-1005'),
                                                                             ('Frank Foster', '1982-02-28', 'MALE', '987 Cedar Ln, Aurora, IL 60505', '555-1006'),
                                                                             ('Grace Green', '1970-12-05', 'FEMALE', '147 Birch Ave, Joliet, IL 60435', '555-1007'),
                                                                             ('Henry Harris', '2015-08-20', 'MALE', '258 Ash Ct, Elgin, IL 60120', '555-1008'),
                                                                             ('Isabel Ivory', '2010-04-12', 'FEMALE', '369 Willow Way, Waukegan, IL 60085', '555-1009'),
                                                                             ('Jack Johnson', '1988-06-25', 'MALE', '741 Oak Park Blvd, Cicero, IL 60804', '555-1010'),
                                                                             ('Karen King', '1993-01-14', 'FEMALE', '852 Lake St, Evanston, IL 60201', '555-1011'),
                                                                             ('Leo Lopez', '1975-10-08', 'MALE', '963 River Rd, Schaumburg, IL 60173', '555-1012'),
                                                                             ('Mary Martinez', '1968-03-22', 'FEMALE', '159 Hill Dr, Bolingbrook, IL 60440', '555-1013'),
                                                                             ('Nathan Nelson', '2005-07-19', 'MALE', '357 Valley View, Palatine, IL 60067', '555-1014'),
                                                                             ('Olivia O''Connor', '1992-11-11', 'FEMALE', '468 Summit Ave, Skokie, IL 60076', '555-1015'),
                                                                             ('Peter Parker', '1987-05-02', 'MALE', '579 Highland Rd, Des Plaines, IL 60016', '555-1016'),
                                                                             ('Quinn Roberts', '1980-09-30', 'OTHER', '680 Forest Ln, Mount Prospect, IL 60056', '555-1017'),
                                                                             ('Rachel Rivera', '1998-02-14', 'FEMALE', '791 Park Pl, Wheaton, IL 60187', '555-1018'),
                                                                             ('Samuel Scott', '1972-12-25', 'MALE', '802 Garden St, Oak Park, IL 60302', '555-1019'),
                                                                             ('Tina Turner', '2008-06-08', 'FEMALE', '913 Spring Rd, Berwyn, IL 60402', '555-1020'),
                                                                             ('Uma Patel', '1984-04-17', 'FEMALE', '124 Winter St, Downers Grove, IL 60515', '555-1021'),
                                                                             ('Victor Vega', '1991-08-29', 'MALE', '235 Summer Ave, Lombard, IL 60148', '555-1022'),
                                                                             ('Wendy Walsh', '1977-01-05', 'FEMALE', '346 Autumn Dr, Glen Ellyn, IL 60137', '555-1023'),
                                                                             ('Xavier Xu', '2012-10-20', 'MALE', '457 Seasons Blvd, Carol Stream, IL 60188', '555-1024'),
                                                                             ('Yolanda Young', '1986-03-08', 'FEMALE', '568 Meadow Ln, Addison, IL 60101', '555-1025'),
                                                                             ('Zachary Zhang', '1994-07-16', 'MALE', '679 Prairie Rd, Bloomingdale, IL 60108', '555-1026'),
                                                                             ('Amanda Adams', '1973-11-23', 'FEMALE', '780 Creek Way, Roselle, IL 60172', '555-1027'),
                                                                             ('Brandon Brown', '2001-05-31', 'MALE', '891 Brook St, Hanover Park, IL 60133', '555-1028'),
                                                                             ('Catherine Clark', '1989-09-09', 'FEMALE', '902 Stream Ave, Bartlett, IL 60103', '555-1029'),
                                                                             ('Daniel Douglas', '1976-02-02', 'MALE', '113 Pond Ct, Glendale Heights, IL 60139', '555-1030');

-- ============================================
-- Sample Medicines (40 common medicines)
-- ============================================
INSERT INTO `medicine` (`name`, `description`, `price`, `stock_quantity`) VALUES
-- Pain & Fever
('Paracetamol 500mg', 'Pain reliever and fever reducer', 0.50, 2000),
('Ibuprofen 400mg', 'Anti-inflammatory pain reliever', 0.80, 1500),
('Aspirin 100mg', 'Pain reliever and blood thinner', 0.30, 1800),
('Naproxen 250mg', 'Anti-inflammatory for pain and fever', 1.20, 800),

-- Antibiotics
('Amoxicillin 250mg', 'Antibiotic for bacterial infections', 1.50, 1200),
('Amoxicillin 500mg', 'Higher dose antibiotic', 2.00, 900),
('Azithromycin 500mg', 'Antibiotic for respiratory infections', 3.50, 600),
('Ciprofloxacin 500mg', 'Broad-spectrum antibiotic', 2.80, 500),
('Metronidazole 400mg', 'Antibiotic for anaerobic infections', 1.80, 700),

-- Gastrointestinal
('Omeprazole 20mg', 'Proton pump inhibitor for acid reflux', 1.50, 1000),
('Ranitidine 150mg', 'H2 blocker for heartburn', 0.90, 850),
('Loperamide 2mg', 'Anti-diarrheal medication', 0.60, 600),
('Metoclopramide 10mg', 'Anti-nausea medication', 1.20, 500),

-- Diabetes
('Metformin 500mg', 'Type 2 diabetes medication', 0.80, 1500),
('Glibenclamide 5mg', 'Sulfonylurea for diabetes', 1.00, 700),
('Insulin Glargine 100IU/ml', 'Long-acting insulin', 45.00, 200),

-- Cardiovascular
('Atorvastatin 20mg', 'Cholesterol-lowering statin', 2.50, 900),
('Amlodipine 5mg', 'Calcium channel blocker for hypertension', 1.20, 1100),
('Losartan 50mg', 'ARB for high blood pressure', 2.00, 850),
('Bisoprolol 5mg', 'Beta-blocker for heart conditions', 1.80, 600),
('Clopidogrel 75mg', 'Antiplatelet agent', 3.00, 500),

-- Respiratory
('Salbutamol Inhaler', 'Bronchodilator for asthma', 8.50, 300),
('Montelukast 10mg', 'Leukotriene receptor antagonist', 2.50, 600),
('Cetirizine 10mg', 'Antihistamine for allergies', 0.70, 1200),
('Loratadine 10mg', 'Non-drowsy antihistamine', 0.80, 1000),

-- Vitamins & Supplements
('Vitamin D3 1000IU', 'Bone health supplement', 0.40, 2000),
('Vitamin B Complex', 'Energy and nerve health', 0.60, 1500),
('Calcium 500mg', 'Bone health supplement', 0.50, 1800),
('Iron 65mg', 'Supplement for anemia', 0.70, 1000),
('Folic Acid 5mg', 'Vitamin B9 supplement', 0.30, 1500),

-- Mental Health
('Fluoxetine 20mg', 'SSRI antidepressant', 2.00, 600),
('Sertraline 50mg', 'SSRI for depression and anxiety', 2.20, 550),
('Diazepam 5mg', 'Benzodiazepine for anxiety', 1.50, 400),
('Alprazolam 0.5mg', 'Short-acting benzodiazepine', 1.80, 350),

-- Dermatology
('Hydrocortisone Cream 1%', 'Topical steroid for skin inflammation', 5.50, 400),
('Clotrimazole Cream 1%', 'Antifungal cream', 6.00, 350),
('Tretinoin Gel 0.025%', 'Retinoid for acne', 12.00, 200),

-- Other
('Prednisolone 5mg', 'Corticosteroid for inflammation', 0.80, 800),
('Levothyroxine 100mcg', 'Thyroid hormone replacement', 1.50, 700),
('Warfarin 5mg', 'Anticoagulant', 0.90, 500);

-- ============================================
-- Sample Appointments (50 appointments - mix of statuses)
-- ============================================
INSERT INTO `appointment` (`patient_id`, `doctor_id`, `date_time`, `status`) VALUES
-- Past completed appointments
(1, 1, '2024-10-15 09:00:00', 'COMPLETED'),
(2, 4, '2024-10-15 10:30:00', 'COMPLETED'),
(3, 6, '2024-10-16 14:00:00', 'COMPLETED'),
(4, 2, '2024-10-17 11:00:00', 'COMPLETED'),
(5, 9, '2024-10-18 15:30:00', 'COMPLETED'),
(6, 11, '2024-10-19 09:30:00', 'COMPLETED'),
(7, 13, '2024-10-20 10:00:00', 'COMPLETED'),
(8, 6, '2024-10-21 14:30:00', 'COMPLETED'),
(9, 7, '2024-10-22 11:30:00', 'COMPLETED'),
(10, 1, '2024-10-23 16:00:00', 'COMPLETED'),
(11, 5, '2024-10-24 09:00:00', 'COMPLETED'),
(12, 10, '2024-10-25 13:00:00', 'COMPLETED'),
(13, 3, '2024-10-26 10:30:00', 'COMPLETED'),
(14, 8, '2024-10-27 15:00:00', 'COMPLETED'),
(15, 14, '2024-10-28 11:00:00', 'COMPLETED'),
(16, 2, '2024-10-29 14:00:00', 'COMPLETED'),
(17, 12, '2024-10-30 09:30:00', 'COMPLETED'),
(18, 1, '2024-10-31 16:30:00', 'COMPLETED'),
(19, 15, '2024-11-01 10:00:00', 'COMPLETED'),
(20, 7, '2024-11-02 14:30:00', 'COMPLETED'),

-- Recent completed appointments with medical records
(21, 4, '2024-11-10 09:00:00', 'COMPLETED'),
(22, 5, '2024-11-10 11:00:00', 'COMPLETED'),
(23, 6, '2024-11-11 14:00:00', 'COMPLETED'),
(24, 11, '2024-11-11 15:30:00', 'COMPLETED'),
(25, 13, '2024-11-12 10:30:00', 'COMPLETED'),

-- Cancelled and no-show
(26, 2, '2024-11-13 09:00:00', 'CANCELLED'),
(27, 3, '2024-11-13 14:00:00', 'NO_SHOW'),
(28, 9, '2024-11-14 11:00:00', 'CANCELLED'),

-- Current/upcoming scheduled appointments
(1, 1, '2024-11-25 09:00:00', 'SCHEDULED'),
(2, 4, '2024-11-25 10:30:00', 'SCHEDULED'),
(3, 6, '2024-11-25 14:00:00', 'SCHEDULED'),
(5, 9, '2024-11-26 09:30:00', 'SCHEDULED'),
(7, 13, '2024-11-26 11:00:00', 'SCHEDULED'),
(10, 1, '2024-11-26 15:00:00', 'SCHEDULED'),
(12, 11, '2024-11-27 10:00:00', 'SCHEDULED'),
(15, 14, '2024-11-27 14:30:00', 'SCHEDULED'),
(18, 2, '2024-11-28 09:00:00', 'SCHEDULED'),
(20, 7, '2024-11-28 11:30:00', 'SCHEDULED'),
(22, 5, '2024-11-29 13:00:00', 'SCHEDULED'),
(24, 11, '2024-11-29 15:00:00', 'SCHEDULED'),

-- Future appointments
(4, 3, '2024-12-02 09:00:00', 'SCHEDULED'),
(8, 8, '2024-12-02 14:00:00', 'SCHEDULED'),
(11, 10, '2024-12-03 10:30:00', 'SCHEDULED'),
(14, 12, '2024-12-03 15:30:00', 'SCHEDULED'),
(16, 15, '2024-12-04 11:00:00', 'SCHEDULED'),
(19, 1, '2024-12-04 14:00:00', 'SCHEDULED'),
(23, 6, '2024-12-05 09:30:00', 'SCHEDULED'),
(25, 13, '2024-12-05 13:30:00', 'SCHEDULED'),
(29, 2, '2024-12-06 10:00:00', 'SCHEDULED'),
(30, 4, '2024-12-06 15:00:00', 'SCHEDULED');

-- ============================================
-- Sample Medical Records (25 records for completed appointments)
-- ============================================
INSERT INTO `medical_record` (`appointment_id`, `symptoms`, `diagnosis`, `notes`) VALUES
                                                                                      (1, 'Persistent cough, fever', 'Upper respiratory tract infection', 'Advised rest and hydration. Prescribed antibiotics.'),
                                                                                      (2, 'Chest pain, shortness of breath', 'Stable angina', 'ECG performed. Started on beta-blockers. Follow-up in 2 weeks.'),
                                                                                      (3, 'Runny nose, sneezing, fever', 'Common cold', 'Symptomatic treatment advised. No antibiotics needed.'),
                                                                                      (4, 'Headache, fatigue', 'Tension headache', 'Stress-related. Recommended relaxation techniques.'),
                                                                                      (5, 'Itchy rash on arms', 'Contact dermatitis', 'Topical steroid prescribed. Avoid allergen exposure.'),
                                                                                      (6, 'Knee pain after fall', 'Sprained ligament', 'Rest, ice, compression advised. Pain medication prescribed.'),
                                                                                      (7, 'Memory issues, confusion', 'Mild cognitive impairment', 'Cognitive tests performed. Referral for neuropsychology evaluation.'),
                                                                                      (8, 'Ear pain, reduced hearing', 'Acute otitis media', 'Antibiotics prescribed. Follow-up in 1 week.'),
                                                                                      (9, 'Sore throat, difficulty swallowing', 'Pharyngitis', 'Viral infection. Symptomatic treatment recommended.'),
                                                                                      (10, 'Abdominal pain, nausea', 'Gastritis', 'PPI prescribed. Dietary modifications advised.'),
                                                                                      (11, 'Irregular heartbeat', 'Atrial fibrillation', 'Started on anticoagulation. Cardiology follow-up scheduled.'),
                                                                                      (12, 'Back pain, muscle stiffness', 'Lumbar strain', 'Physical therapy recommended. Pain management started.'),
                                                                                      (13, 'High blood sugar levels', 'Type 2 diabetes mellitus', 'Lifestyle modifications discussed. Metformin initiated.'),
                                                                                      (14, 'Acne breakout', 'Acne vulgaris', 'Topical retinoid prescribed. Skin care routine discussed.'),
                                                                                      (15, 'Tingling in hands and feet', 'Peripheral neuropathy', 'Vitamin B12 deficiency identified. Supplementation started.'),
                                                                                      (16, 'Seasonal allergies', 'Allergic rhinitis', 'Antihistamine prescribed. Environmental control measures advised.'),
                                                                                      (17, 'Wrist pain', 'Carpal tunnel syndrome', 'Wrist splint recommended. NSAIDs for pain relief.'),
                                                                                      (18, 'Anxiety, insomnia', 'Generalized anxiety disorder', 'Counseling referral. Short-term anxiolytic prescribed.'),
                                                                                      (19, 'Tremors, balance issues', 'Early Parkinson''s disease', 'Neurological assessment completed. Medication initiated.'),
                                                                                      (20, 'Persistent cough, wheezing', 'Asthma exacerbation', 'Inhaler technique reviewed. Oral steroids prescribed.'),
                                                                                      (21, 'Chest discomfort during exercise', 'Coronary artery disease', 'Stress test ordered. Statin and aspirin started.'),
                                                                                      (22, 'Palpitations, dizziness', 'Supraventricular tachycardia', 'Holter monitor ordered. Beta-blocker prescribed.'),
                                                                                      (23, 'Fever, vomiting in child', 'Viral gastroenteritis', 'Oral rehydration advised. Monitor for dehydration.'),
                                                                                      (24, 'Joint pain, swelling', 'Osteoarthritis', 'NSAIDs and physical therapy recommended.'),
                                                                                      (25, 'Severe headache, vision changes', 'Migraine with aura', 'Triptans prescribed. Trigger identification discussed.');

-- ============================================
-- Sample Prescriptions (20 prescriptions)
-- ============================================
INSERT INTO `prescription` (`medical_record_id`, `doctor_id`) VALUES
                                                                  (1, 1),   -- URTI - Dr. John Smith
                                                                  (2, 4),   -- Angina - Dr. Emily Brown
                                                                  (5, 9),   -- Dermatitis - Dr. James Wilson
                                                                  (6, 11),  -- Knee sprain - Dr. William Taylor
                                                                  (7, 13),  -- Cognitive issues - Dr. Richard Moore
                                                                  (8, 6),   -- Otitis - Dr. Jennifer Garcia
                                                                  (10, 1),  -- Gastritis - Dr. John Smith
                                                                  (11, 5),  -- AFib - Dr. David Jones
                                                                  (12, 11), -- Back pain - Dr. William Taylor
                                                                  (13, 3),  -- Diabetes - Dr. Michael Williams
                                                                  (14, 9),  -- Acne - Dr. James Wilson
                                                                  (15, 14), -- Neuropathy - Dr. Linda Jackson
                                                                  (16, 2),  -- Allergies - Dr. Sarah Johnson
                                                                  (17, 12), -- Carpal tunnel - Dr. Patricia Thomas
                                                                  (18, 15), -- Anxiety - Dr. Christopher White
                                                                  (19, 13), -- Parkinson's - Dr. Richard Moore
                                                                  (20, 7),  -- Asthma - Dr. Robert Martinez
                                                                  (21, 4),  -- CAD - Dr. Emily Brown
                                                                  (22, 5),  -- SVT - Dr. David Jones
                                                                  (25, 14); -- Migraine - Dr. Linda Jackson

-- ============================================
-- Sample Prescription Items (medications for each prescription)
-- ============================================
INSERT INTO `prescription_item` (`prescription_id`, `medicine_id`, `quantity`, `dosage`) VALUES
-- Prescription 1: URTI
(1, 5, 21, 'Take 1 capsule 3 times daily for 7 days'),
(1, 1, 30, 'Take 1 tablet every 6 hours as needed for fever'),

-- Prescription 2: Angina
(2, 20, 30, 'Take 1 tablet daily in the morning'),
(2, 3, 30, 'Take 1 tablet daily'),

-- Prescription 3: Dermatitis
(3, 35, 1, 'Apply thin layer to affected area twice daily for 2 weeks'),

-- Prescription 4: Knee sprain
(4, 2, 20, 'Take 1 tablet 3 times daily with food'),

-- Prescription 5: Cognitive issues
(5, 26, 30, 'Take 1 tablet daily'),
(5, 27, 30, 'Take 1 tablet daily'),

-- Prescription 6: Otitis media
(6, 6, 20, 'Take 1 capsule twice daily for 10 days'),
(6, 1, 20, 'Take 1 tablet every 6 hours for pain'),

-- Prescription 7: Gastritis
(7, 10, 30, 'Take 1 capsule daily before breakfast'),

-- Prescription 8: AFib
(8, 21, 30, 'Take 1 tablet daily'),
(8, 39, 30, 'Take as directed by doctor'),

-- Prescription 9: Back pain
(9, 2, 30, 'Take 1 tablet 3 times daily with food'),
(9, 4, 20, 'Take 1 tablet twice daily'),

-- Prescription 10: Diabetes
(10, 14, 60, 'Take 1 tablet twice daily with meals'),

-- Prescription 11: Acne
(11, 37, 1, 'Apply small amount to affected area once nightly'),

-- Prescription 12: Neuropathy
(12, 27, 30, 'Take 1 tablet daily'),
(12, 29, 90, 'Take 1 tablet three times daily'),

-- Prescription 13: Allergies
(13, 24, 30, 'Take 1 tablet daily'),

-- Prescription 14: Carpal tunnel
(14, 2, 30, 'Take 1 tablet twice daily with food'),

-- Prescription 15: Anxiety
(15, 31, 30, 'Take 1 tablet at bedtime'),
(15, 33, 30, 'Take 1 tablet twice daily as needed'),

-- Prescription 16: Parkinson's
(16, 38, 60, 'Take as directed - dosage to be adjusted'),

-- Prescription 17: Asthma
(17, 22, 1, 'Use 2 puffs twice daily'),
(17, 23, 30, 'Take 1 tablet daily at bedtime'),
(17, 38, 10, 'Take 1 tablet daily for 5 days'),

-- Prescription 18: CAD
(18, 17, 30, 'Take 1 tablet daily at bedtime'),
(18, 3, 30, 'Take 1 tablet daily'),

-- Prescription 19: SVT
(19, 20, 30, 'Take 1 tablet daily'),

-- Prescription 20: Migraine
(20, 31, 10, 'Take 1 tablet at onset of migraine'),
(20, 2, 20, 'Take 1 tablet every 6 hours as needed');
