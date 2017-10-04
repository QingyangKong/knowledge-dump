Create entities and bind these entities with DBSet.  
Override `OnModelCreating` and `OnConfiguring`.  
Sample Code:  
```
public class EntityFrameworkTestDBContext: DbContext
    {
        public DbSet<Item> items { get; set; }
        public DbSet<Request> requests { get; set; }
        public DbSet<RequestFile> requestFile { get; set; }

        //method OnModelCreating is only called when models are created not at the time point when the context is instantiated.
        protected override void OnModelCreating(ModelBuilder modelBuilder) {
            modelBuilder.Entity<Item>().ToTable("ITEM");
            modelBuilder.Entity<Item>().HasKey(x => x.itemId);
            modelBuilder.Entity<Item>().Property(x => x.itemId).HasColumnName("ITEM_ID");
            modelBuilder.Entity<Item>().Property(x => x.itemName).HasColumnName("ITEM_NAME");
            modelBuilder.Entity<Item>().Property(x => x.itemDescription).HasColumnName("ITEM_DESCRIPTION");

            modelBuilder.Entity<Request>().ToTable("REQUEST");
            modelBuilder.Entity<Request>().HasKey(x => x.requestId);
            modelBuilder.Entity<Request>().Property(x => x.requestId).HasColumnName("REQUEST_ID");
            modelBuilder.Entity<Request>().Property(x => x.clientId).HasColumnName("CLIENT_ID");
            modelBuilder.Entity<Request>().Property(x => x.creatdBy).HasColumnName("CREATED_BY");
            modelBuilder.Entity<Request>().Property(x => x.updateDate).HasColumnName("UPDATE_DATE");

            modelBuilder.Entity<RequestFile>().ToTable("REQUEST_FILE");
            modelBuilder.Entity<RequestFile>().HasKey(x => x.FileId );
            modelBuilder.Entity<RequestFile>().Property(x => x.FileId).HasColumnName("FILE_ID");
            modelBuilder.Entity<RequestFile>().Property(x => x.RequestId).HasColumnName("REQUEST_ID");
            modelBuilder.Entity<RequestFile>().Property(x => x.File).HasColumnName("FILE");
            base.OnModelCreating(modelBuilder);
        }
        
        //method Onconfiguring is called when the context is instantiated.
        protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder) {
            var cxnStr = ConfigurationManager.AppSettings["ConnectionString"];
            optionsBuilder.UseSqlServer(cxnStr);
            base.OnConfiguring(optionsBuilder);
        }
    }
```
Error "Instance failure" happens because the sql server instance is not set up properly.  
