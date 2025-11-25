-- ============================================
-- Chèn dữ liệu mẫu - Phiên bản rút gọn
-- Phiên bản: 2.0
-- Mô tả: Dữ liệu cốt lõi không bao gồm bảng thuốc/đơn thuốc
-- ============================================

-- ============================================
-- Bác sĩ mẫu (15 bác sĩ theo chuyên khoa)
-- ============================================
INSERT INTO `doctor` (`first_name`, `last_name`, `specialty`, `phone`, `email`) VALUES
('Minh', 'Nguyễn', 'ĐA KHOA', '0901-010-101', 'minh.nguyen@phongkham.vn'),
('Lan', 'Trần', 'ĐA KHOA', '0901-010-102', 'lan.tran@phongkham.vn'),
('Huy', 'Lê', 'ĐA KHOA', '0901-010-103', 'huy.le@phongkham.vn'),

-- Bác sĩ tim mạch
('Thảo', 'Phạm', 'TIM MẠCH', '0902-020-101', 'thao.pham@phongkham.vn'),
('Quang', 'Vũ', 'TIM MẠCH', '0902-020-102', 'quang.vu@phongkham.vn'),

-- Bác sĩ nhi
('Mai', 'Hoàng', 'NHI KHOA', '0903-030-101', 'mai.hoang@phongkham.vn'),
('Tuấn', 'Đặng', 'NHI KHOA', '0903-030-102', 'tuan.dang@phongkham.vn'),
('Vy', 'Bùi', 'NHI KHOA', '0903-030-103', 'vy.bui@phongkham.vn'),

-- Bác sĩ da liễu
('Khánh', 'Đỗ', 'DA LIỄU', '0904-040-101', 'khanh.do@phongkham.vn'),
('Yến', 'Phan', 'DA LIỄU', '0904-040-102', 'yen.phan@phongkham.vn'),

-- Bác sĩ cơ xương khớp
('Long', 'Huỳnh', 'CƠ XƯƠNG KHỚP', '0905-050-101', 'long.huynh@phongkham.vn'),
('Ngọc', 'Châu', 'CƠ XƯƠNG KHỚP', '0905-050-102', 'ngoc.chau@phongkham.vn'),

-- Bác sĩ thần kinh
('Phúc', 'Trương', 'THẦN KINH', '0906-060-101', 'phuc.truong@phongkham.vn'),
('Hạnh', 'Lâm', 'THẦN KINH', '0906-060-102', 'hanh.lam@phongkham.vn'),
('Dũng', 'Tô', 'THẦN KINH', '0906-060-103', 'dung.to@phongkham.vn');

-- ============================================
-- Bệnh nhân mẫu (30 bệnh nhân có social_id)
-- ============================================
INSERT INTO `patient` (`social_id`, `full_name`, `dob`, `gender`, `address`, `phone`, `email`) VALUES
('CCCD001985315', 'Nguyễn Thị Mai Anh', '1985-03-15', 'FEMALE', '123 Đường Lê Lợi, Quận 1, TP.HCM', '0907-100-001', 'maianh.nguyen@example.vn'),
('CCCD002990722', 'Trần Văn Bình', '1990-07-22', 'MALE', '45A Đường Trần Hưng Đạo, Quận 5, TP.HCM', '0907-100-002', 'binh.tran@example.vn'),
('CCCD003978130', 'Lê Thu Cúc', '1978-11-30', 'FEMALE', '87 Nguyễn Thị Minh Khai, Quận 3, TP.HCM', '0907-100-003', 'cuc.le@example.vn'),
('CCCD004200510', 'Phạm Nhật Duy', '2000-05-10', 'MALE', '12 Võ Văn Kiệt, Quận 1, TP.HCM', '0907-100-004', 'duy.pham@example.vn'),
('CCCD005995918', 'Vũ Hồng Em', '1995-09-18', 'FEMALE', '56 Điện Biên Phủ, Quận Bình Thạnh, TP.HCM', '0907-100-005', 'hongem.vu@example.vn'),
('CCCD006982228', 'Đặng Minh Phú', '1982-02-28', 'MALE', '34 Nguyễn Văn Linh, Quận 7, TP.HCM', '0907-100-006', 'phu.dang@example.vn'),
('CCCD007970125', 'Bùi Ngọc Giang', '1970-12-05', 'FEMALE', '90 Lê Quang Định, Quận Gò Vấp, TP.HCM', '0907-100-007', 'giang.bui@example.vn'),
('CCCD008201580', 'Hồ Quốc Huy', '2015-08-20', 'MALE', '18 Đường D2, TP Thủ Đức, TP.HCM', '0907-100-008', 'huy.ho@example.vn'),
('CCCD009201041', 'Tô Bảo Châu', '2010-04-12', 'FEMALE', '210 Võ Thị Sáu, Quận 3, TP.HCM', '0907-100-009', 'chau.to@example.vn'),
('CCCD010988625', 'Đinh Gia Khánh', '1988-06-25', 'MALE', '15 Nguyễn Bỉnh Khiêm, Quận 1, TP.HCM', '0907-100-010', 'khanh.dinh@example.vn'),
('CCCD011993114', 'Võ Tường Lan', '1993-01-14', 'FEMALE', '78 Pasteur, Quận 3, TP.HCM', '0907-100-011', 'lan.vo@example.vn'),
('CCCD012975108', 'Nguyễn Minh Lộc', '1975-10-08', 'MALE', '22 Nguyễn Huệ, Quận 1, TP.HCM', '0907-100-012', 'loc.nguyen@example.vn'),
('CCCD013968322', 'Trương Thị Mỹ Linh', '1968-03-22', 'FEMALE', '135 Lê Văn Sỹ, Quận Phú Nhuận, TP.HCM', '0907-100-013', 'mylinh.truong@example.vn'),
('CCCD014200571', 'Huỳnh Anh Minh', '2005-07-19', 'MALE', '44 Trường Sa, Quận Bình Thạnh, TP.HCM', '0907-100-014', 'minh.huynh@example.vn'),
('CCCD015992111', 'Phan Ngọc Oanh', '1992-11-11', 'FEMALE', '68 Nguyễn Kiệm, Quận Phú Nhuận, TP.HCM', '0907-100-015', 'oanh.phan@example.vn'),
('CCCD016987502', 'Lâm Đức Phong', '1987-05-02', 'MALE', '102 Điện Biên Phủ, TP Thủ Đức, TP.HCM', '0907-100-016', 'phong.lam@example.vn'),
('CCCD017980930', 'Cao Hải Quỳnh', '1980-09-30', 'OTHER', '24 Nguyễn Xí, Quận Bình Thạnh, TP.HCM', '0907-100-017', 'quynh.cao@example.vn'),
('CCCD018998214', 'Mai Thanh Sơn', '1998-02-14', 'MALE', '9 Hoàng Sa, Quận 1, TP.HCM', '0907-100-018', 'son.mai@example.vn'),
('CCCD019972125', 'Đoàn Thị Thu Trang', '1972-12-25', 'FEMALE', '33 Nguyễn Lương Bằng, Quận 7, TP.HCM', '0907-100-019', 'thutrang.doan@example.vn'),
('CCCD020200860', 'Thái Ngọc Uyên', '2008-06-08', 'FEMALE', '120 Lê Lai, Quận 1, TP.HCM', '0907-100-020', 'uyen.thai@example.vn'),
('CCCD021984417', 'Châu Nhật Vinh', '1984-04-17', 'MALE', '12 Nguyễn Văn Cừ, Quận 5, TP.HCM', '0907-100-021', 'vinh.chau@example.vn'),
('CCCD022991829', 'Kiều Thảo Vy', '1991-08-29', 'FEMALE', '50 Lê Văn Việt, TP Thủ Đức, TP.HCM', '0907-100-022', 'thaovy.kieu@example.vn'),
('CCCD023977105', 'Lý Tấn Vũ', '1977-01-05', 'MALE', '76 Phan Xích Long, Quận Phú Nhuận, TP.HCM', '0907-100-023', 'vu.ly@example.vn'),
('CCCD024201210', 'Tạ Diệu Yến', '2012-10-20', 'FEMALE', '8 Song Hành, Quận 12, TP.HCM', '0907-100-024', 'yen.ta@example.vn'),
('CCCD025986308', 'Ngô Đình Phúc', '1986-03-08', 'MALE', '15A Nguyễn Ảnh Thủ, Huyện Hóc Môn, TP.HCM', '0907-100-025', 'phuc.ngo@example.vn'),
('CCCD026994716', 'Trần Bảo Hân', '1994-07-16', 'FEMALE', '27 Trần Não, TP Thủ Đức, TP.HCM', '0907-100-026', 'han.tran@example.vn'),
('CCCD027973123', 'Phùng Minh Ánh', '1973-11-23', 'FEMALE', '99 Huỳnh Tấn Phát, Quận 7, TP.HCM', '0907-100-027', 'minhanh.phung@example.vn'),
('CCCD028200153', 'Đoàn Quốc Bảo', '2001-05-31', 'MALE', '60 Nguyễn Văn Quá, Quận 12, TP.HCM', '0907-100-028', 'bao.doan@example.vn'),
('CCCD029989909', 'Hoàng Thị Cẩm Chi', '1989-09-09', 'FEMALE', '7 Tản Đà, Quận 5, TP.HCM', '0907-100-029', 'camchi.hoang@example.vn'),
('CCCD030976202', 'Vương Đức Dũng', '1976-02-02', 'MALE', '36 Nguyễn Văn Thủ, Quận 1, TP.HCM', '0907-100-030', 'dung.vuong@example.vn');

-- ============================================
-- Cuộc hẹn mẫu (40 cuộc hẹn, trạng thái đa dạng)
-- ============================================
INSERT INTO `appointment` (`patient_id`, `doctor_id`, `date_time`, `reason`, `status`) VALUES
-- Lịch sử đã hoàn thành (có hồ sơ y khoa)
(1, 1, '2024-10-15 09:00:00', 'Khám sức khỏe định kỳ', 'COMPLETED'),
(2, 4, '2024-10-15 10:30:00', 'Đánh giá đau ngực', 'COMPLETED'),
(3, 6, '2024-10-16 14:00:00', 'Triệu chứng cảm lạnh', 'COMPLETED'),
(4, 2, '2024-10-17 11:00:00', 'Đau đầu kéo dài', 'COMPLETED'),
(5, 9, '2024-10-18 15:30:00', 'Phát ban da', 'COMPLETED'),
(6, 11, '2024-10-19 09:30:00', 'Chấn thương đầu gối', 'COMPLETED'),
(7, 13, '2024-10-20 10:00:00', 'Vấn đề trí nhớ', 'COMPLETED'),
(8, 6, '2024-10-21 14:30:00', 'Đau tai', 'COMPLETED'),
(9, 7, '2024-10-22 11:30:00', 'Đau họng', 'COMPLETED'),
(10, 1, '2024-10-23 16:00:00', 'Vấn đề dạ dày', 'COMPLETED'),
(11, 5, '2024-10-24 09:00:00', 'Đánh trống ngực', 'COMPLETED'),
(12, 10, '2024-10-25 13:00:00', 'Đau lưng', 'COMPLETED'),
(13, 3, '2024-10-26 10:30:00', 'Kiểm tra đường huyết', 'COMPLETED'),
(14, 8, '2024-10-27 15:00:00', 'Điều trị mụn', 'COMPLETED'),
(15, 14, '2024-10-28 11:00:00', 'Tê bì tay chân', 'COMPLETED'),

-- Hoàn thành gần đây
(16, 2, '2024-11-10 09:00:00', 'Dị ứng theo mùa', 'COMPLETED'),
(17, 12, '2024-11-10 14:00:00', 'Đau cổ tay', 'COMPLETED'),
(18, 15, '2024-11-11 10:00:00', 'Triệu chứng lo âu', 'COMPLETED'),
(19, 13, '2024-11-11 15:00:00', 'Khó khăn vận động', 'COMPLETED'),
(20, 7, '2024-11-12 11:00:00', 'Khó thở', 'COMPLETED'),

-- Hủy và vắng mặt
(21, 2, '2024-11-13 09:00:00', 'Tái khám', 'CANCELLED'),
(22, 3, '2024-11-13 14:00:00', 'Khám định kỳ', 'NO_SHOW'),
(23, 9, '2024-11-14 11:00:00', 'Khám da', 'CANCELLED'),

-- Cuộc hẹn sắp diễn ra
(1, 1, '2024-11-25 09:00:00', 'Tái khám tổng quát', 'SCHEDULED'),
(2, 4, '2024-11-25 10:30:00', 'Tái khám tim mạch', 'SCHEDULED'),
(3, 6, '2024-11-25 14:00:00', 'Khám sức khỏe trẻ em', 'SCHEDULED'),
(5, 9, '2024-11-26 09:30:00', 'Tái khám da liễu', 'SCHEDULED'),
(7, 13, '2024-11-26 11:00:00', 'Tư vấn thần kinh', 'SCHEDULED'),
(10, 1, '2024-11-26 15:00:00', 'Khám tổng quát', 'SCHEDULED'),
(12, 11, '2024-11-27 10:00:00', 'Đánh giá vật lý trị liệu', 'SCHEDULED'),
(15, 14, '2024-11-27 14:30:00', 'Kiểm tra dẫn truyền thần kinh', 'SCHEDULED'),
(18, 2, '2024-11-28 09:00:00', 'Tái khám tâm lý', 'SCHEDULED'),
(20, 7, '2024-11-28 11:30:00', 'Quản lý hen suyễn', 'SCHEDULED'),

-- Cuộc hẹn tương lai
(4, 3, '2024-12-02 09:00:00', 'Quản lý đái tháo đường', 'SCHEDULED'),
(8, 8, '2024-12-02 14:00:00', 'Tư vấn da liễu', 'SCHEDULED'),
(11, 10, '2024-12-03 10:30:00', 'Điều trị đau lưng', 'SCHEDULED'),
(14, 12, '2024-12-03 15:30:00', 'Đánh giá chỉnh hình', 'SCHEDULED'),
(16, 15, '2024-12-04 11:00:00', 'Đánh giá thần kinh', 'SCHEDULED'),
(19, 1, '2024-12-04 14:00:00', 'Tái khám Parkinson', 'SCHEDULED'),
(23, 6, '2024-12-05 09:30:00', 'Tiêm chủng nhi khoa', 'SCHEDULED'),
(25, 13, '2024-12-05 13:30:00', 'Kiểm soát đau nửa đầu', 'SCHEDULED');

-- ============================================
-- Hồ sơ y khoa mẫu (20 cuộc hẹn hoàn thành)
-- Mỗi INSERT tách riêng để tránh lỗi chuỗi nhiều dòng
-- ============================================

INSERT INTO `medical_record` (`appointment_id`, `symptoms`, `diagnosis`, `treatment`) VALUES
    (1, 'Ho kéo dài, sốt', 'Nhiễm khuẩn hô hấp trên', 'Amoxicillin 500mg: uống 1 viên 3 lần mỗi ngày trong 7 ngày. Paracetamol 500mg: uống 1 viên mỗi 6 giờ khi sốt. Nghỉ ngơi và uống nhiều nước. Tái khám sau 1 tuần nếu không đỡ.');

INSERT INTO `medical_record` (`appointment_id`, `symptoms`, `diagnosis`, `treatment`) VALUES
    (2, 'Đau ngực, khó thở', 'Đau thắt ngực ổn định', 'Bisoprolol 5mg: uống 1 viên vào buổi sáng. Aspirin 100mg: uống 1 viên mỗi ngày. Thay đổi lối sống: giảm căng thẳng, ăn nhạt. Đã làm điện tim, kết quả bất thường. Hẹn đo gắng sức và tái khám sau 2 tuần.');

INSERT INTO `medical_record` (`appointment_id`, `symptoms`, `diagnosis`, `treatment`) VALUES
    (3, 'Sổ mũi, hắt hơi, sốt nhẹ', 'Cảm lạnh do virus', 'Điều trị triệu chứng, nghỉ ngơi và uống nước ấm. Không dùng kháng sinh. Có thể dùng thuốc giảm đau không kê đơn khi cần. Dự kiến khỏi sau 5-7 ngày.');

INSERT INTO `medical_record` (`appointment_id`, `symptoms`, `diagnosis`, `treatment`) VALUES
    (4, 'Đau đầu, mệt mỏi', 'Đau đầu căng thẳng', 'Ibuprofen 400mg: uống 1 viên 3 lần mỗi ngày sau ăn. Hướng dẫn kỹ thuật thư giãn, ngủ đủ 7-8 giờ. Tập thể dục đều. Khuyên áp dụng liệu pháp thư giãn.');

INSERT INTO `medical_record` (`appointment_id`, `symptoms`, `diagnosis`, `treatment`) VALUES
    (5, 'Ngứa nổi mẩn ở tay', 'Viêm da tiếp xúc', 'Kem hydrocortisone 1%: thoa lớp mỏng 2 lần/ngày trong 2 tuần. Tránh tác nhân gây dị ứng. Dùng xà phòng không mùi và dưỡng ẩm thường xuyên. Tái khám sau 2 tuần nếu không cải thiện.');

INSERT INTO `medical_record` (`appointment_id`, `symptoms`, `diagnosis`, `treatment`) VALUES
    (6, 'Đau gối sau té ngã', 'Giãn dây chằng bên trong', 'Tuân thủ nguyên tắc RICE: nghỉ, chườm lạnh, băng ép, kê cao. Ibuprofen 400mg: uống 1 viên 3 lần/ngày giảm đau. Đeo nẹp gối, chuyển vật lý trị liệu. Tránh chịu lực 2 tuần và tái khám sau 1 tuần.');

INSERT INTO `medical_record` (`appointment_id`, `symptoms`, `diagnosis`, `treatment`) VALUES
    (7, 'Giảm trí nhớ, lú lẫn', 'Suy giảm nhận thức nhẹ', 'Đã làm test trí nhớ. Vitamin D3 1000IU: 1 viên/ngày. Vitamin B Complex: 1 viên/ngày. Khuyến khích luyện tập trí não, giới thiệu khám tâm lý thần kinh. Tái khám sau 1 tháng.');

INSERT INTO `medical_record` (`appointment_id`, `symptoms`, `diagnosis`, `treatment`) VALUES
    (8, 'Đau tai, nghe kém', 'Viêm tai giữa cấp', 'Amoxicillin 500mg: uống 1 viên 2 lần/ngày trong 10 ngày. Paracetamol 500mg: dùng khi đau. Giữ tai khô, không bơi cho đến khi khỏi. Tái khám sau 1 tuần.');

INSERT INTO `medical_record` (`appointment_id`, `symptoms`, `diagnosis`, `treatment`) VALUES
    (9, 'Đau họng, nuốt khó', 'Viêm họng do virus', 'Khám họng đỏ, không mủ. Điều trị triệu chứng, súc nước muối ấm 3-4 lần/ngày. Dùng viên ngậm giảm đau. Uống nhiều nước và nghỉ ngơi. Cải thiện sau 3-5 ngày.');

INSERT INTO `medical_record` (`appointment_id`, `symptoms`, `diagnosis`, `treatment`) VALUES
    (10, 'Đau bụng, buồn nôn', 'Viêm dạ dày', 'Omeprazole 20mg: uống 1 viên trước bữa sáng trong 4 tuần. Ăn nhạt, tránh cay, cà phê, rượu. Chia bữa nhỏ, tránh NSAID. Giảm căng thẳng và tái khám sau 2 tuần.');

INSERT INTO `medical_record` (`appointment_id`, `symptoms`, `diagnosis`, `treatment`) VALUES
    (11, 'Tim đập nhanh, chóng mặt', 'Rung nhĩ', 'Clopidogrel 75mg: 1 viên/ngày. Warfarin 5mg: dùng theo hướng dẫn và theo dõi INR. Điện tim xác định rung nhĩ. Hẹn bác sĩ tim mạch, theo dõi nhịp tim, hạn chế caffeine. Tái khám khẩn sau 3 ngày.');

INSERT INTO `medical_record` (`appointment_id`, `symptoms`, `diagnosis`, `treatment`) VALUES
    (12, 'Đau lưng, cơ cứng', 'Căng cơ vùng thắt lưng', 'Ibuprofen 400mg: 1 viên 3 lần/ngày sau ăn. Naproxen 250mg: 1 viên 2 lần/ngày khi cần. Chuyển vật lý trị liệu, hướng dẫn chườm nóng/lạnh và kéo giãn nhẹ. Tránh mang nặng, tái khám sau 2 tuần.');

INSERT INTO `medical_record` (`appointment_id`, `symptoms`, `diagnosis`, `treatment`) VALUES
    (13, 'Đường huyết tăng, khát nhiều, tiểu nhiều', 'Đái tháo đường type 2 mới chẩn đoán', 'Metformin 500mg: uống 1 viên 2 lần/ngày sau ăn. Kiểm tra đường huyết lúc đói hàng ngày. Hẹn tư vấn dinh dưỡng, đi bộ 30 phút mỗi ngày, mục tiêu giảm 10% cân nặng. Đặt lịch xét nghiệm HbA1c và tái khám sau 2 tuần.');

INSERT INTO `medical_record` (`appointment_id`, `symptoms`, `diagnosis`, `treatment`) VALUES
    (14, 'Mụn nổi ở mặt', 'Trứng cá mức độ trung bình', 'Gel tretinoin 0,025%: thoa lớp mỏng mỗi tối (có thể khô da). Rửa mặt dịu nhẹ 2 lần/ngày, dùng kem dưỡng không dầu và chống nắng hàng ngày. Không nặn mụn. Tái khám sau 6 tuần.');

INSERT INTO `medical_record` (`appointment_id`, `symptoms`, `diagnosis`, `treatment`) VALUES
    (15, 'Tê buốt tay chân', 'Bệnh lý thần kinh ngoại biên do thiếu B12', 'Tiêm Vitamin B12 1000mcg: mỗi tuần 1 lần trong 4 tuần, sau đó mỗi tháng. Vitamin B Complex: 1 viên/ngày. Sắt 65mg: 1 viên 3 lần/ngày. Xét nghiệm cho thấy B12 thấp. Tư vấn bổ sung thực phẩm giàu B12. Tái khám sau 4 tuần.');

INSERT INTO `medical_record` (`appointment_id`, `symptoms`, `diagnosis`, `treatment`) VALUES
    (16, 'Hắt hơi, ngứa mắt, sổ mũi', 'Viêm mũi dị ứng theo mùa', 'Cetirizine 10mg: 1 viên/ngày. Hạn chế ra ngoài khi phấn hoa cao, đóng cửa sổ, dùng máy lọc không khí. Tắm rửa sau khi ra ngoài. Cân nhắc test dị ứng nếu không cải thiện.');

INSERT INTO `medical_record` (`appointment_id`, `symptoms`, `diagnosis`, `treatment`) VALUES
    (17, 'Đau cổ tay, tê bì', 'Hội chứng ống cổ tay', 'Ibuprofen 400mg: 1 viên 2 lần/ngày sau ăn. Đeo nẹp cổ tay vào ban đêm. Đánh giá tư thế làm việc, giảm động tác lặp lại. Chườm đá, chuyển vật lý trị liệu, cân nhắc tiêm corticoid nếu không giảm. Tái khám sau 3 tuần.');

INSERT INTO `medical_record` (`appointment_id`, `symptoms`, `diagnosis`, `treatment`) VALUES
    (18, 'Lo âu, mất ngủ, bồn chồn', 'Rối loạn lo âu lan tỏa', 'Fluoxetine 20mg: uống buổi tối. Alprazolam 0,5mg: 1 viên 2 lần/ngày khi cần (ngắn hạn). Giới thiệu trị liệu tâm lý, tập thư giãn, vận động thường xuyên và vệ sinh giấc ngủ. Tái khám sau 2 tuần.');

INSERT INTO `medical_record` (`appointment_id`, `symptoms`, `diagnosis`, `treatment`) VALUES
    (19, 'Run tay, mất thăng bằng, vận động chậm', 'Parkinson giai đoạn sớm', 'Prednisolone 5mg: dùng theo chỉ định và sẽ điều chỉnh liều. Đã khám thần kinh. Hướng dẫn tập vật lý trị liệu giữ thăng bằng, giới thiệu hoạt động trị liệu và phòng ngừa té ngã. Cung cấp thông tin nhóm hỗ trợ, hẹn chuyên gia rối loạn vận động. Tái khám sau 2 tuần.');

INSERT INTO `medical_record` (`appointment_id`, `symptoms`, `diagnosis`, `treatment`) VALUES
    (20, 'Ho dai dẳng, khò khè, khó thở', 'Đợt bùng phát hen phế quản', 'Bình hít salbutamol: xịt 2 nhát 2 lần/ngày. Montelukast 10mg: uống 1 viên trước ngủ. Prednisolone 5mg: uống 1 viên/ngày trong 5 ngày. Kiểm tra lại kỹ thuật hít, tránh khói bụi lạnh. Theo dõi lưu lượng đỉnh và cung cấp kế hoạch xử trí cơn hen. Tái khám sau 1 tuần.');
