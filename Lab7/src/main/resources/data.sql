IF NOT EXISTS (SELECT 1 FROM Categories)
BEGIN
  INSERT INTO Categories (Name) VALUES (N'Áo'), (N'Quần'), (N'Phụ kiện');
END

IF NOT EXISTS (SELECT 1 FROM Products)
BEGIN
  INSERT INTO Products (Name, Image, Price, CreateDate, Available, CategoryId) VALUES
  (N'MOTIVE T-shirt', 'tshirt.jpg', 159000, GETDATE(), 1, 1),
  (N'Running Shorts', 'shorts.jpg', 189000, GETDATE(), 1, 2),
  (N'Cap', 'cap.jpg', 99000, GETDATE(), 1, 3),
  (N'Compression Tee', 'comp.jpg', 219000, GETDATE(), 1, 1),
  (N'Pickleball Shorts', 'pbs.jpg', 199000, GETDATE(), 1, 2),
  (N'Visor', 'visor.jpg', 129000, GETDATE(), 1, 3);
END
