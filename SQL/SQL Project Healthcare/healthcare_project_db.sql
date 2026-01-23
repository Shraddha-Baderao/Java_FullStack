-- ============================================================
--  HEALTHCARE RECORDS DATABASE
--  MySQL Setup Script
--  Run: mysql -u root -p < healthcare_db.sql
-- ============================================================

DROP DATABASE IF EXISTS healthcare_db;
CREATE DATABASE healthcare_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE healthcare_db;

-- ============================================================
-- TABLE 1: PATIENT
-- ============================================================
CREATE TABLE patient (
    patient_id        INT AUTO_INCREMENT PRIMARY KEY,
    first_name        VARCHAR(50)  NOT NULL,
    last_name         VARCHAR(50)  NOT NULL,
    date_of_birth     DATE         NOT NULL,
    gender            ENUM('Male','Female','Other') NOT NULL,
    phone             VARCHAR(15)  NOT NULL,
    email             VARCHAR(100) UNIQUE,
    address           VARCHAR(255),
    insurance_provider VARCHAR(100),
    insurance_number  VARCHAR(50)
);

-- ============================================================
-- TABLE 2: DOCTOR
-- ============================================================
CREATE TABLE doctor (
    doctor_id        INT AUTO_INCREMENT PRIMARY KEY,
    first_name       VARCHAR(50)  NOT NULL,
    last_name        VARCHAR(50)  NOT NULL,
    specialization   VARCHAR(100) NOT NULL,
    license_number   VARCHAR(50)  UNIQUE NOT NULL,
    phone            VARCHAR(15)  NOT NULL,
    email            VARCHAR(100) UNIQUE,
    department       VARCHAR(100)
);

-- ============================================================
-- TABLE 3: MEDICAL_HISTORY
-- ============================================================
CREATE TABLE medical_history (
    history_id         INT AUTO_INCREMENT PRIMARY KEY,
    patient_id         INT         NOT NULL,
    diagnosis          VARCHAR(255),
    allergies          VARCHAR(255),
    chronic_conditions VARCHAR(255),
    recorded_date      DATE        NOT NULL,
    CONSTRAINT fk_mh_patient FOREIGN KEY (patient_id) REFERENCES patient(patient_id) ON DELETE CASCADE
);

-- ============================================================
-- TABLE 4: MEDICATION
-- ============================================================
CREATE TABLE medication (
    medication_id   INT AUTO_INCREMENT PRIMARY KEY,
    patient_id      INT          NOT NULL,
    medication_name VARCHAR(150) NOT NULL,
    dosage          VARCHAR(50),
    frequency       VARCHAR(100),
    start_date      DATE,
    end_date        VARCHAR(50),
    CONSTRAINT fk_med_patient FOREIGN KEY (patient_id) REFERENCES patient(patient_id) ON DELETE CASCADE
);

-- ============================================================
-- TABLE 5: ENCOUNTER
-- ============================================================
CREATE TABLE encounter (
    encounter_id   INT AUTO_INCREMENT PRIMARY KEY,
    patient_id     INT         NOT NULL,
    doctor_id      INT         NOT NULL,
    encounter_type ENUM('Outpatient','Inpatient','Emergency','Telemedicine') NOT NULL,
    admission_date DATETIME    NOT NULL,
    discharge_date DATETIME,
    notes          TEXT,
    CONSTRAINT fk_enc_patient FOREIGN KEY (patient_id) REFERENCES patient(patient_id) ON DELETE CASCADE,
    CONSTRAINT fk_enc_doctor  FOREIGN KEY (doctor_id)  REFERENCES doctor(doctor_id)  ON DELETE RESTRICT
);

-- ============================================================
-- TABLE 6: DIAGNOSTIC_TEST
-- ============================================================
CREATE TABLE diagnostic_test (
    test_id      INT AUTO_INCREMENT PRIMARY KEY,
    encounter_id INT          NOT NULL,
    test_name    VARCHAR(150) NOT NULL,
    test_type    VARCHAR(100),
    test_date    DATE         NOT NULL,
    result       VARCHAR(255),
    unit         VARCHAR(50),
    status       ENUM('Normal','Abnormal','Pending','Reviewed','Low','High','Positive','Negative','Moderate') DEFAULT 'Pending',
    CONSTRAINT fk_dt_encounter FOREIGN KEY (encounter_id) REFERENCES encounter(encounter_id) ON DELETE CASCADE
);

-- ============================================================
-- TABLE 7: PRESCRIPTION
-- ============================================================
CREATE TABLE prescription (
    prescription_id      INT AUTO_INCREMENT PRIMARY KEY,
    encounter_id         INT  NOT NULL,
    doctor_id            INT  NOT NULL,
    medication_id        INT  NOT NULL,
    dosage_instructions  TEXT,
    quantity             INT,
    refills              INT  DEFAULT 0,
    prescribed_date      DATE NOT NULL,
    expiry_date          DATE,
    CONSTRAINT fk_rx_encounter  FOREIGN KEY (encounter_id)  REFERENCES encounter(encounter_id)   ON DELETE CASCADE,
    CONSTRAINT fk_rx_doctor     FOREIGN KEY (doctor_id)     REFERENCES doctor(doctor_id)          ON DELETE RESTRICT,
    CONSTRAINT fk_rx_medication FOREIGN KEY (medication_id) REFERENCES medication(medication_id)  ON DELETE RESTRICT
);

-- ============================================================
-- TABLE 8: PROCEDURE_RECORD
-- (named procedure_record to avoid MySQL reserved word conflict)
-- ============================================================
CREATE TABLE procedure_record (
    procedure_id   INT AUTO_INCREMENT PRIMARY KEY,
    encounter_id   INT          NOT NULL,
    doctor_id      INT          NOT NULL,
    procedure_name VARCHAR(200) NOT NULL,
    procedure_type VARCHAR(100),
    performed_at   DATETIME     NOT NULL,
    outcome        VARCHAR(255),
    notes          TEXT,
    CONSTRAINT fk_pr_encounter FOREIGN KEY (encounter_id) REFERENCES encounter(encounter_id) ON DELETE CASCADE,
    CONSTRAINT fk_pr_doctor    FOREIGN KEY (doctor_id)    REFERENCES doctor(doctor_id)        ON DELETE RESTRICT
);


-- ============================================================
-- SEED DATA: PATIENTS (12 records)
-- ============================================================
INSERT INTO patient (first_name, last_name, date_of_birth, gender, phone, email, address, insurance_provider, insurance_number) VALUES
('Aarav',   'Sharma',    '1985-03-12', 'Male',   '9876543210', 'aarav.sharma@email.com',    '12 MG Road, Pune',         'Star Health',    'SH-100234'),
('Priya',   'Patel',     '1990-07-22', 'Female', '9823456781', 'priya.patel@email.com',     '45 Nehru Nagar, Nagpur',   'HDFC Ergo',      'HE-200345'),
('Rohan',   'Mehta',     '1978-11-05', 'Male',   '9765432190', 'rohan.mehta@email.com',     '7 FC Road, Pune',          'New India',      'NI-300456'),
('Sneha',   'Joshi',     '2000-01-30', 'Female', '9712345678', 'sneha.joshi@email.com',     '23 Wardha Road, Nagpur',   'ICICI Lombard',  'IL-400567'),
('Vikram',  'Rao',       '1965-09-14', 'Male',   '9698765432', 'vikram.rao@email.com',      '88 Tilak Road, Akola',     'United India',   'UI-500678'),
('Deepa',   'Nair',      '1995-04-18', 'Female', '9643210987', 'deepa.nair@email.com',      '3 Hill View, Mumbai',      'Star Health',    'SH-600789'),
('Arjun',   'Singh',     '1988-12-03', 'Male',   '9587654321', 'arjun.singh@email.com',     '19 Sadar Bazar, Delhi',    'Bajaj Allianz',  'BA-700890'),
('Meera',   'Iyer',      '1972-06-25', 'Female', '9534567890', 'meera.iyer@email.com',      '66 Anna Nagar, Chennai',   'Max Bupa',       'MB-800901'),
('Karan',   'Gupta',     '1982-08-09', 'Male',   '9478901234', 'karan.gupta@email.com',     '11 Civil Lines, Lucknow',  'Religare',       'RL-900012'),
('Ananya',  'Desai',     '1997-02-14', 'Female', '9423456789', 'ananya.desai@email.com',    '34 Law Garden, Ahmedabad', 'HDFC Ergo',      'HE-100123'),
('Suresh',  'Pillai',    '1960-10-30', 'Male',   '9367890123', 'suresh.pillai@email.com',   '5 MG Marg, Kochi',         'New India',      'NI-110234'),
('Kavya',   'Krishnan',  '2003-05-07', 'Female', '9312345678', 'kavya.krishnan@email.com',  '77 Brigade Road, Bangalore','ICICI Lombard', 'IL-120345');


-- ============================================================
-- SEED DATA: DOCTORS (10 records)
-- ============================================================
INSERT INTO doctor (first_name, last_name, specialization, license_number, phone, email, department) VALUES
('Rajesh',  'Kumar',   'Cardiology',        'MCI-10001', '9900001111', 'r.kumar@hospital.com',   'Cardiology'),
('Sunita',  'Verma',   'Neurology',         'MCI-10002', '9900002222', 's.verma@hospital.com',   'Neurology'),
('Amit',    'Bose',    'Orthopedics',       'MCI-10003', '9900003333', 'a.bose@hospital.com',    'Orthopedics'),
('Pooja',   'Saxena',  'General Medicine',  'MCI-10004', '9900004444', 'p.saxena@hospital.com',  'General'),
('Naveen',  'Reddy',   'Gastroenterology',  'MCI-10005', '9900005555', 'n.reddy@hospital.com',   'Gastroenterology'),
('Anita',   'Menon',   'Dermatology',       'MCI-10006', '9900006666', 'a.menon@hospital.com',   'Dermatology'),
('Vivek',   'Sharma',  'Oncology',          'MCI-10007', '9900007777', 'v.sharma@hospital.com',  'Oncology'),
('Rekha',   'Jain',    'Gynecology',        'MCI-10008', '9900008888', 'r.jain@hospital.com',    'Gynecology'),
('Sunil',   'Tiwari',  'Pulmonology',       'MCI-10009', '9900009999', 's.tiwari@hospital.com',  'Pulmonology'),
('Neha',    'Agarwal', 'Endocrinology',     'MCI-10010', '9900010000', 'n.agarwal@hospital.com', 'Endocrinology');


-- ============================================================
-- SEED DATA: MEDICAL_HISTORY (12 records)
-- ============================================================
INSERT INTO medical_history (patient_id, diagnosis, allergies, chronic_conditions, recorded_date) VALUES
(1,  'Hypertension',          'Penicillin',   'High BP, Stress',          '2020-01-15'),
(2,  'Type 2 Diabetes',       'None',         'Diabetes Mellitus',        '2019-06-10'),
(3,  'Osteoarthritis',        'Sulfa drugs',  'Arthritis',                '2018-03-22'),
(4,  'Migraine',              'Aspirin',      'Chronic headaches',        '2021-09-05'),
(5,  'Coronary Artery Disease','Ibuprofen',   'CAD, Hypertension',        '2016-11-30'),
(6,  'Asthma',                'Dust mites',   'Allergic Asthma',          '2022-02-14'),
(7,  'Anemia',                'None',         'Iron deficiency',          '2021-07-19'),
(8,  'PCOD',                  'Latex',        'Polycystic Ovary',         '2017-04-08'),
(9,  'Peptic Ulcer',          'NSAIDs',       'GERD, Ulcer',              '2020-12-01'),
(10, 'Anxiety Disorder',      'None',         'Generalised Anxiety',      '2023-01-25'),
(11, 'COPD',                  'Codeine',      'Chronic Bronchitis',       '2015-08-14'),
(12, 'Acne Vulgaris',         'None',         'None',                     '2023-05-11');


-- ============================================================
-- SEED DATA: MEDICATION (12 records)
-- ============================================================
INSERT INTO medication (patient_id, medication_name, dosage, frequency, start_date, end_date) VALUES
(1,  'Amlodipine',      '5mg',    'Once daily',   '2020-02-01', 'Ongoing'),
(2,  'Metformin',       '500mg',  'Twice daily',  '2019-07-01', 'Ongoing'),
(3,  'Diclofenac',      '75mg',   'Once daily',   '2021-01-10', '2021-07-10'),
(4,  'Sumatriptan',     '50mg',   'As needed',    '2021-09-10', 'Ongoing'),
(5,  'Atorvastatin',    '20mg',   'Once daily',   '2016-12-05', 'Ongoing'),
(6,  'Salbutamol',      '100mcg', 'As needed',    '2022-03-01', 'Ongoing'),
(7,  'Ferrous Sulfate', '200mg',  'Twice daily',  '2021-08-01', '2022-02-01'),
(8,  'Metformin',       '850mg',  'Once daily',   '2017-05-01', 'Ongoing'),
(9,  'Omeprazole',      '20mg',   'Once daily',   '2021-01-01', '2021-06-01'),
(10, 'Escitalopram',    '10mg',   'Once daily',   '2023-02-01', 'Ongoing'),
(11, 'Tiotropium',      '18mcg',  'Once daily',   '2015-09-01', 'Ongoing'),
(12, 'Clindamycin',     '150mg',  'Twice daily',  '2023-05-15', '2023-08-15');


-- ============================================================
-- SEED DATA: ENCOUNTER (12 records)
-- ============================================================
INSERT INTO encounter (patient_id, doctor_id, encounter_type, admission_date, discharge_date, notes) VALUES
(1,  1,  'Outpatient', '2024-01-10 09:00:00', '2024-01-10 10:30:00', 'BP follow-up visit'),
(2,  10, 'Outpatient', '2024-01-12 11:00:00', '2024-01-12 12:00:00', 'Quarterly diabetes review'),
(3,  3,  'Inpatient',  '2024-02-01 08:00:00', '2024-02-05 14:00:00', 'Knee replacement surgery'),
(4,  2,  'Outpatient', '2024-02-10 14:00:00', '2024-02-10 15:00:00', 'Acute migraine episode'),
(5,  1,  'Emergency',  '2024-02-15 03:00:00', '2024-02-18 12:00:00', 'Chest pain, suspected ACS'),
(6,  9,  'Outpatient', '2024-03-01 10:00:00', '2024-03-01 11:00:00', 'Asthma flare-up management'),
(7,  4,  'Outpatient', '2024-03-05 09:30:00', '2024-03-05 10:30:00', 'Anemia workup and review'),
(8,  8,  'Outpatient', '2024-03-10 11:00:00', '2024-03-10 12:30:00', 'PCOD routine management'),
(9,  5,  'Inpatient',  '2024-03-15 07:00:00', '2024-03-17 15:00:00', 'Peptic ulcer treatment'),
(10, 4,  'Outpatient', '2024-03-20 10:00:00', '2024-03-20 11:00:00', 'Anxiety counseling session'),
(11, 9,  'Inpatient',  '2024-04-01 06:00:00', '2024-04-07 11:00:00', 'COPD acute exacerbation'),
(12, 6,  'Outpatient', '2024-04-08 14:00:00', '2024-04-08 15:00:00', 'Acne treatment review');


-- ============================================================
-- SEED DATA: DIAGNOSTIC_TEST (12 records)
-- ============================================================
INSERT INTO diagnostic_test (encounter_id, test_name, test_type, test_date, result, unit, status) VALUES
(1,  'Blood Pressure',            'Vitals',        '2024-01-10', '140/90',           'mmHg',  'Abnormal'),
(2,  'HbA1c',                     'Blood Test',    '2024-01-12', '7.8',              '%',     'Abnormal'),
(3,  'X-Ray Knee',                'Radiology',     '2024-02-01', 'Moderate OA',      '-',     'Reviewed'),
(4,  'MRI Brain',                 'Radiology',     '2024-02-10', 'No lesion found',  '-',     'Normal'),
(5,  'ECG',                       'Cardiology',    '2024-02-15', 'ST Depression',    '-',     'Abnormal'),
(6,  'Spirometry',                'Pulmonology',   '2024-03-01', 'FEV1 68%',         '%',     'Abnormal'),
(7,  'Complete Blood Count',      'Blood Test',    '2024-03-05', 'Hb 9.2',           'g/dL',  'Low'),
(8,  'Pelvic Ultrasound',         'Radiology',     '2024-03-10', 'Multiple cysts',   '-',     'Reviewed'),
(9,  'Upper GI Endoscopy',        'GI',            '2024-03-15', 'Gastric ulcer',    '-',     'Abnormal'),
(10, 'GAD-7 Anxiety Scale',       'Psychological', '2024-03-20', 'Score 14',         '-',     'Moderate'),
(11, 'Chest X-Ray',               'Radiology',     '2024-04-01', 'Hyperinflation',   '-',     'Abnormal'),
(12, 'Skin Culture',              'Microbiology',  '2024-04-08', 'P. acnes positive','-',     'Positive');


-- ============================================================
-- SEED DATA: PRESCRIPTION (12 records)
-- ============================================================
INSERT INTO prescription (encounter_id, doctor_id, medication_id, dosage_instructions, quantity, refills, prescribed_date, expiry_date) VALUES
(1,  1,  1,  '1 tablet at night with water',            30,  5,  '2024-01-10', '2024-07-10'),
(2,  10, 2,  '1 tablet after breakfast and dinner',     60,  3,  '2024-01-12', '2024-07-12'),
(3,  3,  3,  '1 tablet after meals for 6 months',       180, 0,  '2024-02-01', '2024-08-01'),
(4,  2,  4,  '1 tablet at onset of migraine only',      10,  2,  '2024-02-10', '2024-08-10'),
(5,  1,  5,  '1 tablet at bedtime daily',               30,  11, '2024-02-18', '2025-02-18'),
(6,  9,  6,  '2 puffs as needed for breathlessness',    1,   3,  '2024-03-01', '2024-09-01'),
(7,  4,  7,  '1 tablet after each meal',                60,  1,  '2024-03-05', '2024-09-05'),
(8,  8,  8,  '1 tablet after dinner',                   30,  5,  '2024-03-10', '2024-09-10'),
(9,  5,  9,  '1 capsule before breakfast on empty stomach', 30, 1, '2024-03-17', '2024-09-17'),
(10, 4,  10, '1 tablet each morning with food',         30,  5,  '2024-03-20', '2024-09-20'),
(11, 9,  11, '1 capsule inhaled every morning',         30,  11, '2024-04-07', '2025-04-07'),
(12, 6,  12, '1 capsule twice daily with food',         28,  1,  '2024-04-08', '2024-07-08');


-- ============================================================
-- SEED DATA: PROCEDURE_RECORD (12 records)
-- ============================================================
INSERT INTO procedure_record (encounter_id, doctor_id, procedure_name, procedure_type, performed_at, outcome, notes) VALUES
(3,  3,  'Total Knee Replacement',             'Surgery',        '2024-02-02 08:00:00', 'Successful',         'Right knee; implant placed'),
(5,  1,  'Coronary Angiography',               'Diagnostic',     '2024-02-15 05:00:00', '70% LAD stenosis',   'LAD involvement confirmed'),
(5,  1,  'Percutaneous Coronary Intervention', 'Intervention',   '2024-02-16 09:00:00', 'Successful',         'Drug-eluting stent placed'),
(9,  5,  'Upper GI Endoscopy',                 'Diagnostic',     '2024-03-15 09:00:00', 'Ulcer confirmed',    'Biopsy sample collected'),
(11, 9,  'Bronchoscopy',                       'Diagnostic',     '2024-04-02 08:00:00', 'Mucus plugging',     'Bronchoalveolar lavage done'),
(9,  5,  'Stomach Biopsy',                     'Biopsy',         '2024-03-15 09:30:00', 'H. pylori negative', 'Awaiting culture results'),
(3,  3,  'Post-op Physical Therapy Setup',     'Rehabilitation', '2024-02-05 10:00:00', 'Initiated',          '6-week physiotherapy plan'),
(8,  8,  'Ovarian Cyst Aspiration',            'Minor Surgery',  '2024-03-11 10:00:00', 'Successful',         'Left ovary; fluid drained'),
(11, 9,  'Non-invasive Ventilation',           'Intervention',   '2024-04-03 14:00:00', 'Partial response',   'BiPAP applied overnight'),
(2,  10, 'HbA1c Monitoring & Counseling',      'Monitoring',     '2024-01-12 11:30:00', 'Reviewed',           'Quarterly glucose target set'),
(6,  9,  'Nebulization Therapy',               'Therapy',        '2024-03-01 10:30:00', 'Relief achieved',    'Salbutamol nebulized in clinic'),
(4,  2,  'Transcranial Magnetic Stimulation',  'Therapy',        '2024-02-10 14:30:00', 'Partial relief',     'Migraine TMS protocol applied');


-- ============================================================
-- USEFUL VIEWS
-- ============================================================

-- Full patient encounter summary
CREATE VIEW vw_patient_encounter_summary AS
SELECT
    p.patient_id,
    CONCAT(p.first_name, ' ', p.last_name) AS patient_name,
    p.gender,
    CONCAT(d.first_name, ' ', d.last_name) AS doctor_name,
    d.specialization,
    e.encounter_type,
    e.admission_date,
    e.discharge_date,
    e.notes
FROM encounter e
JOIN patient p ON e.patient_id = p.patient_id
JOIN doctor  d ON e.doctor_id  = d.doctor_id;

-- Active prescriptions
CREATE VIEW vw_active_prescriptions AS
SELECT
    CONCAT(p.first_name, ' ', p.last_name) AS patient_name,
    m.medication_name,
    m.dosage,
    m.frequency,
    rx.dosage_instructions,
    rx.refills,
    rx.prescribed_date,
    rx.expiry_date,
    CONCAT(d.first_name, ' ', d.last_name) AS prescribed_by
FROM prescription rx
JOIN encounter  e ON rx.encounter_id  = e.encounter_id
JOIN patient    p ON e.patient_id     = p.patient_id
JOIN doctor     d ON rx.doctor_id     = d.doctor_id
JOIN medication m ON rx.medication_id = m.medication_id;

-- Abnormal test results
CREATE VIEW vw_abnormal_tests AS
SELECT
    CONCAT(p.first_name, ' ', p.last_name) AS patient_name,
    dt.test_name,
    dt.test_type,
    dt.test_date,
    dt.result,
    dt.unit,
    dt.status
FROM diagnostic_test dt
JOIN encounter e ON dt.encounter_id = e.encounter_id
JOIN patient   p ON e.patient_id    = p.patient_id
WHERE dt.status IN ('Abnormal','Low','High','Positive','Moderate');


-- ============================================================
-- VERIFY: Row counts
-- ============================================================
SELECT 'patient'          AS table_name, COUNT(*) AS total_records FROM patient
UNION ALL
SELECT 'doctor',                  COUNT(*) FROM doctor
UNION ALL
SELECT 'medical_history',         COUNT(*) FROM medical_history
UNION ALL
SELECT 'medication',              COUNT(*) FROM medication
UNION ALL
SELECT 'encounter',               COUNT(*) FROM encounter
UNION ALL
SELECT 'diagnostic_test',         COUNT(*) FROM diagnostic_test
UNION ALL
SELECT 'prescription',            COUNT(*) FROM prescription
UNION ALL
SELECT 'procedure_record',        COUNT(*) FROM procedure_record;
